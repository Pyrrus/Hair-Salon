## Step 1
 * set the build.gradle by the project name and public static void main(String[] args)

## step 2
 * 'gradle compileJava' to compile the java files

## step 3
 * 'gradle test' to test your code

## Other
 * set your code to match with your requirements.

In PSQL:

CREATE DATABASE hair_salon;
CREATE TABLE stylists (id serial PRIMARY KEY, name varchar, hair_style text, other_skills text);
CREATE TABLE clients (id serial PRIMARY KEY, name varchar, likes text, stylist_id int);

CREATE DATABASE hair_salon_test;
CREATE TABLE stylists (id serial PRIMARY KEY, name varchar, hairStyle text, otherSkills text);
CREATE TABLE clients (id serial PRIMARY KEY, name varchar, likes text, stylist_id int);