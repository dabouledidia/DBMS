import mysql.connector
import pandas as pd

def join_csv():
  fertility_df = pd.read_csv('data/age_specific_fertility_rates.csv', encoding="ISO-8859-1")
  fertility_df = fertility_df[['country_code', 'year','total_fertility_rate','gross_reproduction_rate','sex_ratio_at_birth']]
  fertility_df = fertility_df.fillna(0)

  growth_df = pd.read_csv('data/birth_death_growth_rates.csv', encoding="ISO-8859-1")
  growth_df = growth_df[['country_code', 'year','crude_birth_rate','crude_death_rate','net_migration','net_migration','growth_rate']]
  growth_df = growth_df.fillna(0)

  population_df = pd.read_csv('data/midyear_population.csv', encoding="ISO-8859-1")
  population_df = population_df[['country_code', 'year','midyear_population']]
  population_df = population_df.fillna(0)

  expect_df = pd.read_csv('data/mortality_life_expectancy.csv', encoding="ISO-8859-1")
  expect_df = expect_df[['country_code', 'year','infant_mortality','infant_mortality_male','infant_mortality_female','life_expectancy','life_expectancy_male','life_expectancy_female']]
  expect_df = expect_df.fillna(0)

  merged_1 = fertility_df.merge(population_df, on=['country_code', 'year'],how='right')
  merged_2 = growth_df.merge(expect_df, on=['country_code', 'year'],how='left')

  merged = merged_2.merge(merged_1, on=['country_code', 'year'],how='right')

  return merged




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

join_csv()