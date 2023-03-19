import mysql.connector
import config

mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="1234",
  use_unicode=True,
  charset="utf8"
)

mycursor = mydb.cursor()

mycursor.execute("DROP DATABASE IF EXISTS dbms")

mycursor.execute("CREATE DATABASE dbms")
mycursor.execute("USE dbms")

countries_table = '''CREATE TABLE Countries(
                id INT NOT NULL AUTO_INCREMENT,
                Code VARCHAR(10),
                Display_Name VARCHAR(100),
                Continent VARCHAR(100),
                Currency_Name VARCHAR(100),
                Area_SqKm INT,
                Population INT,
                PRIMARY KEY(id),
                INDEX (Code))
                ENGINE = InnoDB'''


statistics_table = '''CREATE TABLE Statistics(
                id INT NOT NULL AUTO_INCREMENT,
                Country VARCHAR(10),
                Year INT,
                Indicator VARCHAR(100),
                Value DOUBLE,
                PRIMARY KEY(id),
                FOREIGN KEY (`Country`) REFERENCES `Countries` (`Code`) ON DELETE CASCADE,
                FOREIGN KEY (`Indicator`) REFERENCES `Indicators` (`Name`) ON DELETE CASCADE)

                ENGINE = InnoDB'''

indicators_table = '''CREATE TABLE Indicators(
                id INT NOT NULL AUTO_INCREMENT,
                Name VARCHAR(100),
                Clean_Name VARCHAR(100),
                PRIMARY KEY(id),
                Index(Name))
                ENGINE = InnoDB'''

mycursor.execute(countries_table)
mycursor.execute(indicators_table)
mycursor.execute(statistics_table)


countries_df = config.config_countries_df()

cols = "`,`".join([str(i) for i in countries_df.columns.tolist()])

for i,row in countries_df.iterrows():
  sql = "INSERT INTO `Countries` (`" +cols + "`) VALUES (" + "%s,"*(len(row)-1) + "%s)"
  mycursor.execute(sql, tuple(row))

  mydb.commit()

demographics_df = config.join_csv()
final_income_dfs = config.read_xlsx()
indicators_list,indicators_list_clean = config.get_indicators()

cols = list(demographics_df.columns)
del cols[0:2]

for indicator in range(len(indicators_list)):

    sql = "INSERT INTO Indicators (Name,Clean_Name) VALUES (%s,%s)"
    vals = (indicators_list[indicator],indicators_list_clean[indicator])

    mycursor.execute(sql, vals)

for income_df in final_income_dfs:
    for index,row in income_df.iterrows():
      sql = "INSERT INTO Statistics (Country, Year, Indicator, Value) VALUES (%s, %s, %s, %s)"
      vals = (row[3],row[0],row[2],row[1])
      mycursor.execute(sql, vals)
      print(index)



for index,row in demographics_df.iterrows():
  for col in cols:
    print(index,row[0])

    sql = "INSERT INTO Statistics (Country, Year, Indicator, Value) VALUES (%s, %s, %s, %s)"
    vals = (row[0],row[1],col,row[col])

    mycursor.execute(sql, vals)



mydb.commit()
mydb.close()
print("Done.")