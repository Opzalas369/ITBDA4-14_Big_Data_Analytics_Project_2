-- Question 4.3: import the cleaned CSV into PostgreSQL and analyse it

-- 1. Create the table in the Project_2 schema
CREATE SCHEMA IF NOT EXISTS "Project_2";
DROP TABLE IF EXISTS "Project_2".programmes;
CREATE TABLE "Project_2".programmes (
    title          TEXT PRIMARY KEY,
    faculty        TEXT,
    programme_type TEXT,
    learning_modes TEXT,
    description    TEXT,
    start_date     DATE,
    n_modes        INTEGER,
    desc_words     INTEGER
);

-- 2. Import Q4_2_cleaned.csv with the pgAdmin Import/Export tool (csv, header on),
--    or with: COPY "Project_2".programmes FROM 'path/to/Q4_2_cleaned.csv' WITH (FORMAT csv, HEADER true);

-- 3. Check the import
SELECT COUNT(*) AS records FROM "Project_2".programmes;

-- Query 1: how many programmes does each faculty offer, and how flexible are they?
-- RANK() is a window function that ranks the faculties by number of programmes
SELECT faculty,
       COUNT(*)                   AS programmes,
       ROUND(AVG(n_modes), 2)     AS avg_learning_modes,
       RANK() OVER (ORDER BY COUNT(*) DESC) AS size_rank
FROM "Project_2".programmes
GROUP BY faculty
ORDER BY size_rank;

-- Query 2: which programme types can be studied part time?
-- ~* is the case-insensitive POSIX regular expression match
SELECT programme_type,
       COUNT(*) AS programmes,
       COUNT(*) FILTER (WHERE learning_modes ~* 'part time') AS part_time,
       ROUND(100.0 * COUNT(*) FILTER (WHERE learning_modes ~* 'part time') / COUNT(*)) AS pct_part_time
FROM "Project_2".programmes
GROUP BY programme_type
ORDER BY pct_part_time DESC, programmes DESC;
