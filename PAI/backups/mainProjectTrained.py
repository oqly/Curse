import keras as k
import keras.utils.np_utils
import numpy as np
import pandas as pd
import pymorphy2
import re
import csv

data_frame = pd.read_csv("groups.csv", encoding="utf-16")
input_names = ["Themes", "Names"]  # "Priority"
output_names = ["Faculty"]

raw_input_data = data_frame[input_names]
raw_output_data = data_frame[output_names]

# Библиотека со словарём слов для их нормализации
ma = pymorphy2.MorphAnalyzer()


# Очистка текста
def clean_text(text):
    text = text.replace('""', ' ').replace(',', ' ')
    text = text.lower()
    text = re.sub('[.,:;_%©?*,!@#$%^&()\d]|[+=]|[┋]|[[]|[]]|[/]|"|\s{2,}|-', ' ', text)
    from numpy import unicode  # ?????
    text = " ".join(ma.parse(unicode(word))[0].normal_form for word in text.split())
    text = ' '.join(word for word in text.split() if len(word) > 1)
    return text


data_frame["Themes_cl"] = data_frame.apply(lambda x: clean_text(x["Themes"]), axis=1)
data_frame["Names_cl"] = data_frame.apply(lambda x: clean_text(x["Names"]), axis=1)
print(data_frame["Themes_cl"])
print(data_frame["Names_cl"])

# Превращение Факультетов в числовые обозначения
categories = {}
for key, value in enumerate(data_frame["Faculty"].unique()):
    categories[value] = key + 1

data_frame['faculty_code'] = data_frame["Faculty"].map(categories)
print("cat: {}".format(categories))
print(data_frame["faculty_code"])

uniq = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
for i in data_frame["faculty_code"]:
    uniq[data_frame["faculty_code"][i]-1] += 1
print(uniq)

'''
file = open('faculties.csv', 'w', newline='', encoding='utf-16')
with file:
    writer = csv.writer(file)
    writer.writerows(map(lambda x: [x], data_frame["faculty_code"]))
'''

total_categories = len(data_frame["faculty_code"]) + 1
print('Всего строк с факультетами: {}'.format(total_categories))

# Перемешиваем строки
# data_frame = data_frame.sample(frac=1).reset_index(drop=True)

themes = data_frame["Themes_cl"]
names = data_frame["Names_cl"]
faculty = data_frame["faculty_code"]
current = names
namesFlag = True

# Будем пока работать с Тематиками, позже можно будет добавить Имена

from keras.preprocessing.text import Tokenizer

# создаем единый словарь (слово -> число) для преобразования
tokenizer = Tokenizer()
tokenizer.fit_on_texts(current.tolist())

# Преобразуем все Тематики в числовые последовательности, заменяя слова на числа по словарю.
textSequences = tokenizer.texts_to_sequences(current.tolist())

# Максимальная длина
max_words = 0
for th in current.tolist():
    words = len(th.split())
    if words > max_words:
        max_words = words
print('Максимальная длина тематик: {} слов'.format(max_words))

total_unique_words = len(tokenizer.word_counts)
print('Всего уникальных слов в словаре: {}'.format(total_unique_words))

maxSequenceLength = max_words


# Создаём обучающие и тестовые наборы данных
def load_data_from_arrays(strings, labels, train_test_split=0.8):
    data_size = len(strings)
    test_size = int(data_size - round(data_size * train_test_split))
    print("Test size: {}".format(test_size))

    print("\nTraining set:")
    x_train = strings[test_size:]
    print("\t - x_train: {}".format(len(x_train)))
    y_train = labels[test_size:]
    print("\t - y_train: {}".format(len(y_train)))

    print("\nTesting set:")
    x_test = strings[:test_size]
    print("\t - x_test: {}".format(len(x_test)))
    y_test = labels[:test_size]
    print("\t - y_test: {}".format(len(y_test)))

    return x_train, y_train, x_test, y_test


X_train, y_train, X_test, y_test = load_data_from_arrays(current.tolist(), faculty, train_test_split=0.8)

# total_words = len(tokenizer.word_index)
# print('В словаре {} слов'.format(total_words))

if namesFlag:
    vocab_size = round(total_unique_words / 10)
else:
    vocab_size = total_unique_words + 1

from keras.utils import np_utils
from tensorflow.keras.preprocessing import sequence

num_classes = np.max(y_train) + 1
print('Количество категорий для классификации: {}'.format(num_classes))

print(u'Преобразуем описания заявок в векторы чисел...')
tokenizer = Tokenizer(num_words=vocab_size)
tokenizer.fit_on_texts(current)
print(X_train)

X_train = tokenizer.texts_to_sequences(X_train)
X_test = tokenizer.texts_to_sequences(X_test)

X_train = sequence.pad_sequences(X_train, maxlen=maxSequenceLength)
X_test = sequence.pad_sequences(X_test, maxlen=maxSequenceLength)

print('Размерность X_train:', X_train.shape)
print('Размерность X_test:', X_test.shape)

print(u'Преобразуем категории в матрицу двоичных чисел '
      u'(для использования categorical_crossentropy)')
y_train = k.utils.np_utils.to_categorical(y_train, num_classes)  # num_classes
y_test = k.utils.np_utils.to_categorical(y_test, num_classes)  # num_classes
print('y_train shape:', y_train.shape)
print('y_test shape:', y_test.shape)

import numpy as np
from sklearn.preprocessing import LabelEncoder

'''
encoder = LabelEncoder()
#y_train=y_train.reshape(-1,1)
#y_test=y_test.reshape(-1,1)
encoder.fit(y_train)
y_train = encoder.transform(y_train)
y_test = encoder.transform(y_test)
'''

from keras.models import Sequential
from keras.layers import Dense, Embedding, LSTM

# максимальное количество слов для анализа
max_features = vocab_size

print(u'Загружаем модель...')
model = k.models.load_model("weights\\n20model.h5")

print(u'Результат оценки')
predicted_test = model.predict(X_test)
print(predicted_test)
print(u'Перевод оценки')

predicted = []

for pr in predicted_test.tolist():
    max = 0
    for value in pr:
        if value > max:
            max = value
    predicted.append(list(categories.keys())[list(categories.values()).index(pr.index(max))+1])
print(predicted)