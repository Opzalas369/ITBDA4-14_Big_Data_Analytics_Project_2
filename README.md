# ITBDA4-14 Big Data Analytics Techniques, Project 2

Sherman Opperman, CON-1375611-S5S8, Eduvos Tyger Valley. Lecturer: Gilbert Lekula.

The project document with all four questions is in `docs/Project2_Document.docx`.

## Folders

| Folder | Content |
|---|---|
| `docs/` | The project document (Questions 1 to 4) |
| `datasets/map_reduce_test_docs/` | doc1.txt to doc7.txt, the input of Question 1 |
| `datasets/sa_news/` | news_1.txt to news_10.txt, the input of Question 2 |
| `Q1/` | Hadoop MapReduce keyword location search in Java: Mapper, Reducer, Driver, run steps and the real outputs |
| `Q2/` | Topic modelling (R notebook), data frame, topic tables and charts |
| `Q3/` | Web scraping and summarisation (R notebook), scraped data, summaries and charts |
| `Q4/` | ETL with R and PostgreSQL (R notebook), raw and cleaned data, SQL script |

## How to run

- **Q1:** Hadoop 3.2.4 and Java 8. The steps are in `Q1/Q1_run_steps.txt`.
- **Q2 to Q4:** open the notebook in the question folder with an R kernel and run all the cells from the top. The packages used are rvest, httr, stringr, tm, topicmodels, igraph, ggplot2 and wordcloud.
- **Q4 SQL:** `Q4/Q4_3_postgres.sql` creates the table in the `Project_2` schema. Import `Q4/Q4_2_cleaned.csv` with the pgAdmin Import/Export tool, then run the queries.
