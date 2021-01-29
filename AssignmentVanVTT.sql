CREATE DATABASE BookManagement
USE BookManagement
CREATE TABLE AccountTBL(
	username varchar(50) PRIMARY KEY,
	password varchar(50) DEFAULT '123456',
	firstname varchar(50),
	lastname varchar(50),
	email varchar(50) UNIQUE,
	phone varchar(20) UNIQUE,
	gender varchar(20),
	address varchar(500),
	description varchar(4000),
	role varchar(50),
	isDisable bit DEFAULT 0,
	isStillWorking bit,
	dateOfCreate datetime DEFAULT GETDATE(),
)
CREATE TABLE BookCategoryTBL(
    bookCategoryID varchar(10) PRIMARY KEY,
	name varchar(200),
)
CREATE TABLE BookTBL
(
    bookID varchar(10) PRIMARY KEY,
	title varchar(200),
	author varchar(200),
	description varchar(4000),
	shortDescription varchar(400),
	thumbnailImg varchar(200),
	mainImg varchar(200),
	price decimal(18,2),
	releasedDate datetime,
	issuers varchar(200),
	publishingCompany varchar(200),
	size varchar(100),
	coverType varchar(100),
	numOfPage int,
	categoryBookID varchar(10) FOREIGN KEY REFERENCES BookCategoryTBL(bookCategoryID),
	isDisable bit DEFAULT 0,
	stillProducting bit DEFAULT 1,
	dateOfCreate datetime DEFAULT GETDATE(),
)
CREATE TABLE OrderTBL(
    orderID varchar(100) PRIMARY KEY,
	order_by varchar(50) FOREIGN KEY REFERENCES AccountTBL(username),
	paymentMethod varchar(50),
	status varchar(20) DEFAULT 'waiting',
	shipAddress nvarchar(500),
	total decimal(18,2),
	phone varchar(50),
	fullname varchar(500),
	order_date datetime DEFAULT GETDATE(),
)
CREATE TABLE OrderDetailTBL(
    orderDetailID varchar(100) PRIMARY KEY,
    orderID varchar(100) FOREIGN KEY REFERENCES OrderTBL(orderID),
	bookID varchar(10) FOREIGN KEY REFERENCES BookTBL(bookID),
	quantity int, 
	price decimal(18,2), 
)
Insert Into BookCategoryTBL(bookCategoryID, name)
Values(1, 'Computers & Technology')
Insert Into BookCategoryTBL(bookCategoryID, name)
Values(2, 'Self-help')
Insert Into BookCategoryTBL(bookCategoryID, name)
Values(3, 'Comics & Graphic Novels')
Insert Into BookCategoryTBL(bookCategoryID, name)
Values(4, 'Cookbooks, Food & Wine')
Insert Into BookCategoryTBL(bookCategoryID, name)
Values(5, 'Education & Teaching')
Insert Into BookCategoryTBL(bookCategoryID, name)
Values(6, 'Engineering & Transportation')
Insert Into BookTBL(bookID, title, shortDescription, description, 
author, mainImg, price, categoryBookID, releasedDate, issuers, publishingCompany,
                    size, coverType, numOfPage, thumbnailImg)
Values('B-1','Clean Code: A Handbook of Agile Software Craftsmanship','Helped bring agile principles from a practitioners point of view',
'Even bad code can function. But if code isn?t clean, it can bring a development organization to its knees. Every year, countless hours and significant resources are lost because of poorly written code. But it doesn?t have to be that way.

Noted software expert Robert C. Martin presents a revolutionary paradigm with Clean Code: A Handbook of Agile Software Craftsmanship. Martin has teamed up with his colleagues from Object Mentor to distill their best agile practice of cleaning code ?on the fly? into a book that will instill within you the values of a software craftsman and make you a better programmer?but only if you work at it.

What kind of work will you be doing? You?ll be reading code?lots of code. And you will be challenged to think about what?s right about that code, and what?s wrong with it. More importantly, you will be challenged to reassess your professional values and your commitment to your craft.

Clean Code is divided into three parts. The first describes the principles, patterns, and practices of writing clean code. The second part consists of several case studies of increasing complexity. Each case study is an exercise in cleaning up code?of transforming a code base that has some problems into one that is sound and efficient. The third part is the payoff: a single chapter containing a list of heuristics and ?smells? gathered while creating the case studies. The result is a knowledge base that describes the way we think when we write, read, and clean code.

Readers will come away from this book understanding
How to tell the difference between good and bad code
How to write good code and how to transform bad code into good code
How to create good names, good functions, good objects, and good classes
How to format code for maximum readability
How to implement complete error handling without obscuring code logic
How to unit test and practice test-driven development
This book is a must for any developer, software engineer, project manager, team lead, or systems analyst with an interest in producing better code.',
'Robert C. Martin','themes/images/books/B1_1.jpg,themes/images/books/B1_2.jpg',
38.99,1,'2008-08-01','Pearson','Amazon','7 x 1.05 x 9.15 inches','Soft',464,'themes/images/books/B1.jpg')
Insert Into BookTBL(bookID, title, shortDescription, description, 
author, mainImg, price, categoryBookID, releasedDate, issuers, publishingCompany,
                    size, coverType, numOfPage, thumbnailImg)
Values('B-2','Trumpty Dumpty Wanted a Crown: Verses for a Despotic Age','Trumpty Dumpty Wanted a Crown is darker and more hard-hitting than ever',
'Trumpty Dumpty Wanted a Crown is darker and more hard-hitting than ever. Lithgow writes and draws with wit and fury as he takes readers through another year of the shocking events involving Trump and his administration. His uproarious poems and illustrations encompass Trump impeachment, the COVID-19 pandemic, the Black Lives Matter protests, and much more. Lithgow targets Mitch McConnell, Mike Pompeo, Bill Barr, Jared Kushner, Elaine Chao, and many others, but also includes a few heroes of the moment, including Anthony Fauci, Nancy Pelosi, Adam Schiff, and even Barack Obama.

The book arrives at a time when its needed most. With all-new poems and never-before-seen line drawings, Lithgow will once again make readers laugh and pause to remember some of the most defining moments in recent history�skewering the reign of King Dumpty one stanza at a time.',
'John Lithgow','themes/images/books/B2_1.jpg,themes/images/books/B2_2.jpg',
20.66,2,'2009-08-01','Pearson','Amazon','7 x 1.05 x 9.15 inches','Soft',104,'themes/images/books/B2.jpg')
Insert Into BookTBL(bookID, title, shortDescription, description, 
author, mainImg, price, categoryBookID, releasedDate, issuers, publishingCompany,
                    size, coverType, numOfPage, thumbnailImg)
Values('B-3','The Haunting of Brynn Wilder: A Novel','Helped bring agile principles from a practitioners point of view',
'Even bad code can function. But if code isn?t clean, it can bring a development organization to its knees. Every year, countless hours and significant resources are lost because of poorly written code. But it doesn?t have to be that way.

Noted software expert Robert C. Martin presents a revolutionary paradigm with Clean Code: A Handbook of Agile Software Craftsmanship. Martin has teamed up with his colleagues from Object Mentor to distill their best agile practice of cleaning code ?on the fly? into a book that will instill within you the values of a software craftsman and make you a better programmer?but only if you work at it.

What kind of work will you be doing? You?ll be reading code?lots of code. And you will be challenged to think about what?s right about that code, and what?s wrong with it. More importantly, you will be challenged to reassess your professional values and your commitment to your craft.

Clean Code is divided into three parts. The first describes the principles, patterns, and practices of writing clean code. The second part consists of several case studies of increasing complexity. Each case study is an exercise in cleaning up code?of transforming a code base that has some problems into one that is sound and efficient. The third part is the payoff: a single chapter containing a list of heuristics and ?smells? gathered while creating the case studies. The result is a knowledge base that describes the way we think when we write, read, and clean code.

Readers will come away from this book understanding
How to tell the difference between good and bad code
How to write good code and how to transform bad code into good code
How to create good names, good functions, good objects, and good classes
How to format code for maximum readability
How to implement complete error handling without obscuring code logic
How to unit test and practice test-driven development
This book is a must for any developer, software engineer, project manager, team lead, or systems analyst with an interest in producing better code.',
'Wendy Webb','themes/images/books/B3_1.jpg,themes/images/books/B3_2.jpg',
12.33,3,'2008-06-01','Pearson','Amazon','7 x 1.05 x 9.15 inches','Soft',374,'themes/images/books/B3.jpg')
Insert Into AccountTBL(username, password, firstname, lastname, email, phone, gender, address, description, role, isStillWorking)
Values('A-1','123456','Thien Phuc','Hoang Gia','phuchgt@gmail.com','0989875757','F','','','manager', 1)
Insert Into AccountTBL(username, password, firstname, lastname, email, phone, gender, address, description, role, isStillWorking)
Values('A-2','123456','Khanh Vy','Tran','vykt@gmail.com','0989875758','F','','','employee', 1)
Insert Into AccountTBL(username, password, firstname, lastname, email, phone, gender, address, description, role)
Values('A-3','123456','Gia Hao','Cao','haocg@gmail.com','0989875759','F','','','customer')