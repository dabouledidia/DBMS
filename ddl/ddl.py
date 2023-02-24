import mysql.connector
import pandas as pd

mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="1234"
)

mycursor = mydb.cursor()

mycursor.execute("DROP DATABASE IF EXISTS dbms")

mycursor.execute("CREATE DATABASE dbms")
mycursor.execute("USE dbms")

countries_table = '''CREATE TABLE Countries(
                id INT NOT NULL AUTO_INCREMENT,
                ISO VARCHAR(10),
                Display_Name VARCHAR(100),
                Continent VARCHAR(100),
                CurrencyName VARCHAR(100),
                Area_SqKm INT,
                Population INT,
                PRIMARY KEY(id))
                ENGINE = InnoDB'''

mycursor.execute(countries_table)

mycursor.execute("SHOW TABLES")

for x in mycursor:
  print(x)

countries_df = pd.read_csv('data/countries.csv', encoding="ISO-8859-1")
countries_df = countries_df[['ISO', 'Display_Name','Continent','CurrencyName','Area_SqKm','Population']]
countries_df = countries_df.fillna(0)

cols = "`,`".join([str(i) for i in countries_df.columns.tolist()])

for i,row in countries_df.iterrows():
  sql = "INSERT INTO `Countries` (`" +cols + "`) VALUES (" + "%s,"*(len(row)-1) + "%s)"
  mycursor.execute(sql, tuple(row))

    # the connection is not autocommitted by default, so we must commit to save our changes
  mydb.commit()


