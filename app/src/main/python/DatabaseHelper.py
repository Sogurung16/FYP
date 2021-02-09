import pandas as pd
import numpy as np
import os
import csv
"""from sklearn.metrics.pairwise import cosine_similarity
sklearn.feature_extraction.text import CountVectorizer"""
import sqlite3

def create_connection(db_file):
    connection = sqlite3.connect(db_file)
    return connection

def select_all_tasks(connection):

    cur = connection.cursor()
    cur.execute("SELECT * FROM activities_table")

    rows = cur.fetchall()

    for row in rows:
        print(row)

    cursor = connection.cursor()
    cus
    with open("activities_data.csv", "w") as csvfile:
        csvwriter = csv.writer(csvfile, delimiter=",")
        csvwriter.writerow([i[0] for i in cur.description])
        csvwriter.writerows(cur)

    dirpath = os.getcwd() + "/activities_data.csv"
    print("Data exported Successfully into {}".format(dirpath))


#put attributes in bag_of_words
#use cosine_similarity against bag_of_words
#recommend top 5 activities

def main():
    database = r"D:\UniversityHDD\FYP\Database.db"

    connection = create_connection(database)
    select_all_tasks(connection)
    df = pd.read_sql_query("SELECT * FROM activities_table", connection)
    dfcsv = pd.read_csv("activities_data.csv")
    #print(df)
    #print(dfcsv)
    """
    with connection:
        print("Activities Table")
        select_all_tasks(connection)
"""
main()
