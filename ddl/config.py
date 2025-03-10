import pandas as pd


def join_csv():
  fertility_df = pd.read_csv('../data/age_specific_fertility_rates.csv', encoding="ISO-8859-1")
  fertility_df = fertility_df[['country_code', 'year','total_fertility_rate','gross_reproduction_rate','sex_ratio_at_birth']]
  fertility_df = fertility_df.fillna(-1)

  growth_df = pd.read_csv('../data/birth_death_growth_rates.csv', encoding="ISO-8859-1")
  growth_df = growth_df[['country_code', 'year','crude_birth_rate','crude_death_rate','net_migration','rate_natural_increase','growth_rate']]
  growth_df = growth_df.fillna(-1)

  population_df = pd.read_csv('../data/midyear_population.csv', encoding="ISO-8859-1")
  population_df = population_df[['country_code', 'year','midyear_population']]
  population_df = population_df.fillna(-1)

  expect_df = pd.read_csv('../data/mortality_life_expectancy.csv', encoding="ISO-8859-1")
  expect_df = expect_df[['country_code', 'year','infant_mortality','infant_mortality_male','infant_mortality_female','life_expectancy','life_expectancy_male','life_expectancy_female']]
  expect_df = expect_df.fillna(-1)

  merged_1 = fertility_df.merge(population_df, on=['country_code', 'year'],how='right')
  merged_2 = growth_df.merge(expect_df, on=['country_code', 'year'],how='right')

  merged = merged_2.merge(merged_1, on=['country_code', 'year'],how='right')

  # print(merged_1,merged_2,merged)
  merged = merged.fillna(-1)

  return merged


def read_xlsx():
  lst_income_dfs = []
  income_dfs = pd.read_excel('../data/income.xlsx', sheet_name=None, engine='openpyxl')
  for k, v in income_dfs.items():
    v = v.melt('Country')
    v.rename(columns={"variable": "Year"}, inplace=True)
    v['Indicator'] = k
    lst_income_dfs.append(v)

  countries_df = config_countries_df()
  code_country_df = countries_df[['Code', 'Display_Name']]
  code_country_df.rename(columns={"Display_Name": "Country"}, inplace=True)
  final_income_dfs = []

  for income_df in lst_income_dfs:
    income_df = pd.merge(income_df, code_country_df, on='Country')
    income_df = income_df.drop('Country', axis=1)
    income_df = income_df.replace('..',-1)
    final_income_dfs.append(income_df)

  return final_income_dfs


def config_countries_df():
  countries_df = pd.read_csv('../data/countries.csv')
  countries_df = countries_df[['FIPS', 'Display_Name','Continent','CurrencyName','Area_SqKm','Population']]
  countries_df.rename(columns={"FIPS": "Code"}, inplace=True)
  countries_df.rename(columns={"CurrencyName": "Currency_Name"}, inplace=True)
  countries_df = countries_df.fillna(-1)
  return countries_df

def get_indicators():
  stats_df = join_csv()
  income_dfs = read_xlsx()
  income_list = []
  for income_df in income_dfs:
    income_list.append(list(set(income_df['Indicator']))[0])
  indicators_list = list(stats_df.columns) + income_list
  del indicators_list[0:2]

  indicators_list_cleaned = []
  for ind in list(stats_df.columns):
    ind_cleaned = ind.replace('_',' ')
    indicators_list_cleaned.append(ind_cleaned.capitalize())
  indicators_list_cleaned = indicators_list_cleaned+income_list
  del indicators_list_cleaned[0:2]

  return indicators_list,indicators_list_cleaned

print(get_indicators())