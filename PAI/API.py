from flask import Flask, request
from flask_restful import Resource, Api
# from flask.ext.jsonpify import jsonify

import parserOneId
import csv
import vk
import keras as k
import keras.utils.np_utils
import numpy as np
import pandas as pd
import pymorphy2
import re

context = ('server/server.crt', 'server/server.key')

app = Flask(__name__)
api = Api(app)

categories = []
result = []


def defVk():
    v = 5.131
    token = "491398f8beb499f928d26099c8b9c7805e6926d1a08087a66adf41cf2daeca8be997f33db0454f7bd516a"  # access token
    session = vk.Session(access_token=token)  # Авторизация
    vk_api = vk.API(session, v=v)
    return vk_api


def getGroupsById(id, vk_api):
    groups_data = [["id", "Themes", "Names", "Priority", "Faculty"]]  # , "Количество групп"
    groups = parserOneId.get_groups(id, vk_api)
    result = parserOneId.collect_data_csv(id, groups)
    if result:
        groups_data.append(result)
    file = open('groups.csv', 'a', newline='', encoding='utf-16')
    with file:
        writer = csv.writer(file)
        writer.writerow(groups_data[1])
    return


def readResult():
    res = {}
    file = open('result.csv', 'r', newline='', encoding='utf-16')
    with file:
        reader = csv.reader(file)
        row = next(reader)
        for value in row:
            result.append(value)
        row = next(reader)
        for value in row:
            categories.append(value)
    i = 0
    for value in categories:
        res[value] = result[i]
        i += 1
    return res

def readResultData():
    res = {}
    data = []

    file = open('result.csv', 'r', newline='', encoding='utf-16')
    with file:
        reader = csv.reader(file)
        row = next(reader)
        for value in row:
            result.append(value)
        row = next(reader)
        for value in row:
            categories.append(value)
    i = 0
    for value in categories:
        temp = dict()
        temp["faculty"] = value
        temp["similarity"] = result[i]
        data.append(temp)
        i += 1
    res["data"] = data
    return res


class Faculty(Resource):
    def get(self):
        # exec(open("parserOneId.py").read())
        args = request.args
        id = args['id']
        vk_api = defVk()
        getGroupsById(id, vk_api)
        import mainProjectTrainedDef as PrDef
        PrDef.createResult()
        #exec(open("mainProjectTrained.py", encoding="utf-8").read())
        res = readResultData()
        return res


api.add_resource(Faculty, '/faculty')  # Route_1

if __name__ == '__main__':
    app.run(host='0.0.0.0')  # ssl_context=context
