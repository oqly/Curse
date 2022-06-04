import keras as k
import pandas as pd
import numpy as np
import matplotlib as plt

data_frame = pd.read_csv("titanic.csv")
input_names = ["Age", "Sex", "Pclass"]
output_names = ["Survived"]

max_age = 100
encoders = {"Age": lambda age: [age/max_age],
            "Sex": lambda gen: {"male": [0], "female": [1]}.get(gen),
            "Pclass": lambda pclass: {1: [1, 0, 0], 2: [0, 1, 0], 3: [0, 0, 1]}.get(pclass),
            "Survived": lambda s_value: [s_value]}

def dataframe_to_dict(df):
    result = dict()
    for column in df.columns:
        values = data_frame[column].values
        result[column] = values
    return result

def make_supervised(df):
    raw_input_data = data_frame[input_names]
    raw_output_data = data_frame[output_names]
    return  {"inputs": dataframe_to_dict(raw_input_data),
             "outputs": dataframe_to_dict(raw_output_data)}

def encode(data):
    vectors = []
    for data_name, data_values in data.items():
        encoded = list(map(encoders[data_name], data_values))
        vectors.append(encoded)
    formatted = []
    for vector_raw in list(zip(*vectors)):
        vector = []
        for element in vector_raw:
            for e in element:
                vector.append(e)
        formatted.append(vector)
    return formatted



supervised = make_supervised(data_frame)
encoded_inputs = np.array(encode(supervised["inputs"]))
encoded_outputs = np.array(encode(supervised["outputs"]))

train_x = encoded_inputs[:600]
train_y = encoded_outputs[:600]

test_x = encoded_inputs[600:]
test_y = encoded_outputs[600:]

model = k.models.load_model("mymodel.h5")

predicted_test = model.predict(test_x)
real_data = data_frame.iloc[600:][input_names+output_names]
real_data["PSurvived"] = predicted_test
print(real_data)