import numpy as np
import vk  # Импортируем модуль vk
import csv


def get_groups(userid):  # Функция формирования базы участников сообщества в виде списка
    first = vk_api.groups.get(user_id=userid, extended=1, fields="activity")
    data = first["items"]
    return data


def get_users(univer):
    univerid = vk_api.database.getUniversities(q=univer, country_id=1)["items"][0]["id"]
    users = vk_api.users.search(university=univerid, university_year=2024, fields="universities", count=100)
    #users24 = vk_api.users.search(university=univerid, university_year=2024, fields="universities")
    #for item in users24:
    #    users.append(item)
    return users["items"], univerid


def collect_users(data, univerid):
    criteria = "faculty_name"  # chair_name
    users = []
    for item in data:
        if "universities" in item:
            i = 0
            while item["universities"][i]["id"] != univerid:
                i += 1
            if criteria in item["universities"][i]:
                user = []
                user.append(item["id"])
                user.append(item["universities"][i][criteria])
                users.append(user)
    return users


def save_data(userid, data, filename="user_groups.txt"):  # Функция сохранения базы в txt файле
    with open(filename, "w", encoding='utf-8') as file:
        file.write(str(userid) + "\n")
        for item in data:
            if "activity" in item:
                if not (item["activity"].startswith("Этот материал заблокирован") or (
                        item["activity"] == 'Открытая группа') or (item["activity"] == 'Закрытая группа') or (
                                item["activity"] == 'Частная группа')):
                    file.write(str(item["id"]) + "     " + item["activity"] + "  ||   " + item["name"] + "\n")


# str(item["id"]) + "     " + item["name"] + "     " + item["activity"] + "\n"

def collect_data(userid, data):  # Функция сохранения базы в txt файле
    data_list = [str(userid)]
    for item in data:
        if "activity" in item:
            if not (item["activity"].startswith("Этот материал заблокирован") or (
                    item["activity"] == 'Открытая группа') or (item["activity"] == 'Закрытая группа') or (
                            item["activity"] == 'Частная группа')):
                data_list.append(str(item["activity"]))
    return data_list


def collect_data_csv(userid, data, direction):
    data_list = [str(userid)]
    activities = ""
    group_names = ""
    for item in data:
        if "activity" in item:
            if not (item["activity"].startswith("Этот материал заблокирован") or (
                    item["activity"] == 'Открытая группа') or (item["activity"] == 'Закрытая группа') or (
                            item["activity"] == 'Частная группа')):
                activities += (str(item["activity"]) + ",")
        if "name" in item:
            group_names += (str(item["name"]).replace('"', '') + ",")
    data_list.append(activities[:-1])
    data_list.append(group_names[:-1])
    data_list.append("3,3,3,3,3,3,3,3,3,3,2,2,2,2,2,2,2,2,2,2")
    data_list.append(str(direction)[:-1])
    # data_list.append(str(len(data)))
    return data_list


def save_csv(id_list, direction):
    groups_data = [["id", "Тематики", "Названия", "Приоритеты", "Направление"]]  # , "Количество групп"
    j = 0
    for i in id_list:
        groups = get_groups(i)
        groups_data.append(collect_data_csv(i, groups, direction[j]))
        j += 1
    print(groups_data)
    file = open('groups.csv', 'w', newline='', encoding='utf-16')
    with file:
        writer = csv.writer(file)
        writer.writerows(groups_data)


# "     " + item["name"] +

# def enter_data(filename="data.txt"):  # Функция ввода базы из txt файла

# def get_intersection(group1, group2):  # Функция нахождения пересечений двух баз

# def union_members(group1, group2):  # Функция объединения двух баз без повторов

if __name__ == "__main__":
    v = 5.131
    token = "f1285a514b7a8df16abe0982d86754356f21ec85a57e17746ce82a39a508f16e27a78d55c568fc3f3285e"  # Сервисный ключ доступа
    session = vk.Session(access_token=token)  # Авторизация
    vk_api = vk.API(session, v=v)
    # groups = vk_api.account.getInfo(fields='country')
    current_id = 222261577  # 222261577 169521480

    datausers, universityid = get_users("ЯГТУ")
    users = collect_users(datausers, universityid)
    print(users)

    groups = get_groups(current_id)

    # print(groups)
    save_data(current_id, groups)

    people_id = []  # 169521480, 222261577
    faculty = []  # направление
    file1 = open("users.txt", "r")
    while True:
        line = file1.readline()
        if not line:
            break
        str_split = line.split(",")
        people_id.append(str_split[0])
        faculty.append(str_split[1])
    file1.close()

    save_csv(people_id, faculty)
    # Сохранение в csv
    # groups_data = []
    # for i in people_id:
    #    groups = get_groups(i)
    #    groups_data.append(collect_data(i, groups))
    # np.savetxt("groups_data.csv", np.array(groups_data), fmt='%s', delimiter=",")
