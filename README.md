# Hair Salon site using spark, java and postgresql

## Hair Salon using OOP within Java and database, 9/23/16

#### By **Adam Gorbahn**

## Description
This site will make stylist and client. Update and delete for both of them. 

## User Stories

* As a salon employee, I need to be able to see a list of all our stylists.
* As an employee, I need to be able to select a stylist, see their details, and see a list of all clients that belong to that stylist.
* As an employee, I need to add new stylists to our system when they are hired.
* As an employee, I need to be able to add new clients to a specific stylist.
* As an employee, I need to be able to update a stylist's details.
* As an employee, I need to be able to update a client's details.
* As an employee, I need to be able to delete a stylist if they're no longer employed here.
* As an employee, I need to be able to delete a client if they no longer visit our salon.

## Specifications

#### '/stylist/new' page

user input                | output
------------------------- | -------------
Type in the stylist text fields | giving stylist by the user in the input tag

user input                | output
------------------------- | -------------
click on the "submit" button | This will make the stylist given by the user. The site will send them to the "/list" page. Update page with new stylist. 


#### '/stylist/:id/edit' page (:id for the what stylist you are working on) for example /stylist/1/edit for example

user input                | output
------------------------- | -------------
Type in the text fields for spelling error and update. | giving words by the user in the input tags

user input                | output
------------------------- | -------------
click on the "Update" button | This will update stylist given by the user. The site will send them to the "/stylist/:id" page. Will show the update information from the user.

user input                | output
------------------------- | -------------
click on the "Delete" button | This will remove stylist given by the user. The site will send them to the "/list" page. Will not show the stylist from the user. Also, it will update all the client off the delete stylist.

#### '/client/new' page

user input                | output
------------------------- | -------------
Type in the client text fields | giving client by the user in the input tag

user input                | output
------------------------- | -------------
click on the "submit" button | This will make the client given by the user. The site will send them to the "/list" page. Update page with new client. 


#### '/client/:id/edit' page (:id for the what client you are working on) for example /client/1/edit for example

user input                | output
------------------------- | -------------
Type in the text fields for spelling error and update. | giving words by the user in the input tags

user input                | output
------------------------- | -------------
click on the "Update" button | This will update client given by the user. The site will send them to the "/client/:id" page. Will show the update information from the user.

user input                | output
------------------------- | -------------
click on the "Delete" button | This will remove client given by the user. The site will send them to the "/list" page. Will not show the client from the user.

## Setup/Installation Requirements

* need java, gradle, spark, and Postgresql.

## code

The sql file is in the folder call SQL


```
In Postgresql:

CREATE DATABASE hair_salon;
CREATE TABLE stylists (id serial PRIMARY KEY, name varchar, hairStyle text, otherSkills text);
CREATE TABLE clients (id serial PRIMARY KEY, name varchar, likes text, stylist_id int);

CREATE DATABASE hair_salon_test;
CREATE TABLE stylists (id serial PRIMARY KEY, name varchar, hairStyle text, otherSkills text);
CREATE TABLE clients (id serial PRIMARY KEY, name varchar, likes text, stylist_id int);
```

## Technologies Used

* HTML
* Java
* Spark
* Juint
* Gradle
* Postgresql

### License

*GPL*

Copyright (c) 2016 **Adam Gorbahn**