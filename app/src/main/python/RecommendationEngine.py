import pandas as pd
import numpy as np
from sklearn.metrics.pairwise import cosine_similarity
from sklearn.metrics import precision_score
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from collections import Counter
import sqlite3

database = r"/data/data/com.example.fyp_01/databases/MyDatabase.db"
#database = r"D:/UniversityHDD/FYP/Database.db" # test database

def create_connection(db_file):
    connection = sqlite3.connect(db_file)
    return connection

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
    dfUser = dfUser.drop(columns={'users_id', 'users_points'})
    #drop other rows
    #dfUser = dfUser.head(1)

    #rename columns
    dfUser = dfUser.rename(columns={"users_name":"name","users_goal":"type","users_workout_group":"workoutlevel","users_intensity":"intensity","users_equipment_group":"equipmentgroup"})

    #convert features columns to lowercase
    features = ["type", "workoutlevel", "intensity", "equipmentgroup"]
    for feature in features:
        dfUser[feature] = dfUser[feature].str.lower()
    return dfUser

def userProfile(dfUser):
    name = dfUser.head(1)['name'][0][0]

    typeCounter = Counter(dfUser['type'])
    workoutlevelCounter = Counter(dfUser['workoutlevel'])
    intensityCounter = Counter(dfUser['intensity'])
    equipmentCounter = Counter(dfUser['equipmentgroup'])
    #set feature as the most common feature
    type=typeCounter.most_common(1)[0][0]
    workoutlevel=workoutlevelCounter.most_common(1)[0][0]
    intensity=intensityCounter.most_common(1)[0][0]
    equipmentgroup=equipmentCounter.most_common(1)[0][0]

    user = {'name':[name],'type':[type], 'workoutlevel':[workoutlevel], 'intensity':[intensity], 'equipmentgroup':[equipmentgroup]}
    dfUser = pd.DataFrame(data=user)
    print(dfUser)
    #print(name,'\n',type,'\n',workoutlevel,'\n',intensity,'\n',equipmentgroup,'\n')
    #for index, row in dfUser.iterrows():
        #print(index, row)
    #print(type)
    return dfUser

def createSoup(df):
    return ''.join(df['type'])+' '+''.join(df['workoutlevel'])+' '+''.join(df['intensity'])+' '+''.join(df['equipmentgroup'])

def get_recommendation(name, cosine_sim):
    #set the user preferences index
    idx = indices[name]

    #Get the pairwise similarity scores of all activities against the user preferences
    sim_scores = list(enumerate(cosine_sim[idx]))

    #Sort the activities based on the similarity scores
    sorted_sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)

    #Get the scores of the top n-1 most similar activities
    n = 8
    topN_sim_scores = sorted_sim_scores[1:n]

    #Get the activity indices
    activity_indices = [i[0] for i in topN_sim_scores]

    #Return the top 5 most similar activities
    recommendations = dfActivities['name'].iloc[activity_indices]
    recommendationLines = '\n'.join(recommendations)

    #Recommendation Evaluation
    X = count_matrix[1:].toarray() # count matrix of features
    Y = np.array(sim_scores[1:]) # similarity score against user preference
    Y = Y[:,1]
    print('Every feature:\n',count.get_feature_names())
    print('Count matrix:\n', X)
    print('Pairwise similarity score against user preference:\n', Y)
    print('Top N acitivy id with scores:\n', topN_sim_scores)
    print('Top N activities names:\n', recommendations)


    recommended = np.array([row[1] for row in topN_sim_scores])
    print('Recommended acitvities scores: ',recommended)
    relevant = Y>=0.75
    print('Relevant scores (>=75%): ',relevant)
    not_relevant = Y<0.75
    print('Non Relevant scores (<75%): ',not_relevant)
    recommended_and_relevant = recommended>=0.75
    print('Recommended and Relevant: ',recommended_and_relevant)
    recommended_and_not_relevant = recommended<0.75
    print('Recommended and Not Relevant: ',recommended_and_not_relevant)
    precision = sum(recommended_and_relevant)/len(recommended)
    if(np.isnan(precision)):
        precision = 0
    print('Precision: ', precision*100,'%')
    recall = sum(recommended_and_relevant)/sum(relevant)
    if(np.isnan(recall)):
        recall = 0
    print('Recall: ', recall*100,'%')
    false_positive_rate = sum(recommended_and_not_relevant)/sum(not_relevant)
    if(np.isnan(false_positive_rate)):
        false_positive_rate = 0
    print('False Positive Rate: ', false_positive_rate*100, '%')


    return recommendationLines

def main():
    user = dfUser.iloc[0]['name']
    """if(not user):
        return null
    else:"""

    return get_recommendation(user, cosine_sim)

connection = create_connection(database)
dfActivities = preProcessActivitiesData(connection)
dfUser = preProcessUserData(connection)
dfUser = userProfile(dfUser)

dfActivities = pd.concat([dfUser, dfActivities])

dfActivities['soup'] = dfActivities.apply(createSoup, axis=1)

count = CountVectorizer()
count_matrix = count.fit_transform(dfActivities['soup'])

cosine_sim = cosine_similarity(count_matrix, count_matrix)
dfActivities = dfActivities.reset_index()

indices = pd.Series(dfActivities.index, index=dfActivities['name'])

#main()
