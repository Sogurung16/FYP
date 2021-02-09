import pandas as pd
import numpy as np
import os
import csv
from sklearn.metrics.pairwise import cosine_similarity
from sklearn.feature_extraction.text import CountVectorizer
import sqlite3
from ast import literal_eval

database = r"D:\UniversityHDD\FYP\Database.db"

def create_connection(db_file):
    connection = sqlite3.connect(db_file)
    return connection

def exportCSVFormat(connection):

    cursor = connection.cursor()
    cursor.execute("SELECT * FROM activities_table")
    with open("activities_data.csv", "w") as csvfile:
        csvwriter = csv.writer(csvfile, delimiter=",")
        csvwriter.writerow([i[0] for i in cursor.description])
        csvwriter.writerows(cursor)

    dirpath = os.getcwd() + "/activities_data.csv"
    print("Data exported Successfully into {}".format(dirpath))

    dfcsv = pd.read_csv("activities_data.csv")
    print(dfcsv)

def preProcessActivitiesData(connection):
    dfActivities = pd.read_sql_query("SELECT * FROM activities_table", connection)

    #drop columns
    dfActivities = dfActivities.drop(columns=['activities_image','activities_id'])
    #rename columns
    dfActivities = dfActivities.rename(columns={"activities_name":"name","activities_type":"type","activities_workout_level":"workoutlevel","activities_intensity":"intensity","activities_equipment_group":"equipmentgroup"})

    #convert features columns to lowercase
    features = ["type", "workoutlevel", "intensity", "equipmentgroup"]
    for feature in features:
        dfActivities[feature] = dfActivities[feature].str.lower()
    return dfActivities

def preProcessUserData(connection):
    dfUser = pd.read_sql_query("SELECT * FROM users_table", connection)

    #drop columns
    dfUser = dfUser.drop(columns=['users_id'])
    #drop other rows
    dfUser = dfUser.head(1)

    #rename columns
    dfUser = dfUser.rename(columns={"users_name":"name","users_goal":"type","users_workout_group":"workoutlevel","users_intensity":"intensity","users_equipment_group":"equipmentgroup"})

    #convert features columns to lowercase
    features = ["type", "workoutlevel", "intensity", "equipmentgroup"]
    for feature in features:
        dfUser[feature] = dfUser[feature].str.lower()
    return dfUser

def createSoup(df):
    return ''.join(df['type'])+' '+''.join(df['workoutlevel'])+' '+''.join(df['intensity'])+' '+''.join(df['equipmentgroup'])

def get_recommendation(name, cosine_sim):
    #Get the index of the activity that matches the activities_name
    idx = indices[name]

    #Get the pairwise similarity scores of all activities with that activity
    sim_scores = list(enumerate(cosine_sim[idx]))

    #Sort the movies based on the similarity scores
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)

    #Get the scores of the top 10 most similar activities
    sim_scores = sim_scores[1:6]

    #Get the activity indices
    activity_indices = [i[0] for i in sim_scores]

    #Return the top 10 most similar activities
    return dfActivities['name'].iloc[activity_indices]


def main():
    user = dfUser.iloc[0]['name']
    if(not user):
        print("No recommendations")
    else:
        print(get_recommendation(user, cosine_sim))

connection = create_connection(database)
dfActivities = preProcessActivitiesData(connection)
dfUser = preProcessUserData(connection)

dfActivities = pd.concat([dfUser, dfActivities])

dfActivities['soup'] = dfActivities.apply(createSoup, axis=1)

count = CountVectorizer(stop_words='english')
count_matrix = count.fit_transform(dfActivities['soup'])

cosine_sim = cosine_similarity(count_matrix, count_matrix)

dfActivities = dfActivities.reset_index()

indices = pd.Series(dfActivities.index, index=dfActivities['name'])

main()
