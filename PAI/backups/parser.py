import time

import numpy as np
import vk  # Импортируем модуль vk
import csv


def get_groups(userid):  # Функция формирования базы участников сообщества в виде списка
    first = vk_api.groups.get(user_id=userid, extended=1, fields="activity")
    data = first["items"]
    return data


def get_users(univer):
    univerid = vk_api.database.getUniversities(q=univer, country_id=1)["items"][0]["id"]
    users = vk_api.users.search(university=univerid, university_year=2024, fields="universities", count=200)
    users23 = vk_api.users.search(university=univerid, university_year=2023, fields="universities", count=200)
    users22 = vk_api.users.search(university=univerid, university_year=2022, fields="universities", count=200)
    for item in users23["items"]:
        users["items"].append(item)
    for item in users22["items"]:
        users["items"].append(item)
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
                                item["activity"] == 'Частная группа') or (item["activity"] == 'Публичная страница')):
                    file.write(str(item["id"]) + "     " + item["activity"] + "  ||   " + item["name"] + "\n")


# str(item["id"]) + "     " + item["name"] + "     " + item["activity"] + "\n"

def collect_data(userid, data):  # Функция сохранения базы в txt файле
    data_list = [str(userid)]
    for item in data:
        if "activity" in item:
            if not (item["activity"].startswith("Этот материал заблокирован") or (
                    item["activity"] == 'Открытая группа') or (item["activity"] == 'Закрытая группа') or (
                            item["activity"] == 'Частная группа') or (item["activity"] == 'Публичная страница')):
                data_list.append(str(item["activity"]))
    return data_list


def collect_data_csv(userid, data, faculty):
    data_list = []
    activities = ""
    group_names = ""
    for item in data:
        if "activity" in item:
            if not (item["activity"].startswith("Этот материал заблокирован") or (
                    item["activity"] == 'Открытая группа') or (item["activity"] == 'Закрытая группа') or (
                            item["activity"] == 'Частная группа') or (item["activity"] == 'Публичная страница')):
                activities += (str(item["activity"]) + ",")
        if "name" in item:
            group_names += (str(item["name"]).replace('"', '') + ",")
    if activities != "":
        data_list = [str(userid)]
        data_list.append(activities[:-1])
        data_list.append(group_names[:-1])
        data_list.append("3,3,3,3,3,3,3,3,3,3,2,2,2,2,2,2,2,2,2,2")
        data_list.append(str(faculty))
    # data_list.append(str(len(data)))
    return data_list


def save_csv(users):
    groups_data = [["id", "Themes", "Names", "Priority", "Faculty"]]  # , "Количество групп"
    for i in users:
        groups = get_groups(i[0])
        if len(groups) < 10:
            print(i[0])
        result = collect_data_csv(i[0], groups, i[1])
        if result:
            groups_data.append(result)
        time.sleep(0.1)
    #print(groups_data)
    file = open('groups.csv', 'w', newline='', encoding='utf-16')
    with file:
        writer = csv.writer(file)
        writer.writerows(groups_data)


# "     " + item["name"] +


if __name__ == "__main__":
    v = 5.131
    token = "6e37a7333384dbc076237c9ac4b8b045cd6be48ec01a1a0ccb54e6614f23123847a38cb5188da908e3b9a"  # access token
    session = vk.Session(access_token=token)  # Авторизация
    vk_api = vk.API(session, v=v)

    datausers, universityid = get_users("ЯГТУ")
    users = collect_users(datausers, universityid)
    print(users)
    save_csv(users)