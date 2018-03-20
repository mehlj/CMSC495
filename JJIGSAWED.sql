-- Create local instance of the database

CREATE DATABASE CMSC495;

USE CMSC495;

CREATE TABLE Users(
UserID INT NOT NULL,
Name VARCHAR(100) NOT NULL,
Role VARCHAR(25) NOT NULL,
PRIMARY KEY(UserID)
);

CREATE TABLE Projects(
ProjectID INT NOT NULL, 
ProjectName VARCHAR(200) NOT NULL,
ProjectAssignedTo VARCHAR(100) NOT NULL,
ProjectDue DATE NOT NULL,
ProjectPriority INT NOT NULL,
ProjectSummary VARCHAR(2000) NOT NULL,
PRIMARY KEY (ProjectID)
);

CREATE TABLE Tasks(
TaskID INT NOT NULL,
FKProjectID INT NOT NULL,
FKUserID INT NOT NULL,
TaskName VARCHAR(200) NOT NULL,
TaskDateCreated DATE NOT NULL,
TaskDateEnded DATE,
TaskPriority INT NOT NULL,
TaskSummary VARCHAR(2000) NOT NULL,
FOREIGN KEY (FKProjectID) references Projects(ProjectID),
FOREIGN KEY (FKUserID) references Users(UserID),
PRIMARY KEY(TaskID)
);

CREATE UNIQUE INDEX prjIDX ON Projects(ProjectID);
CREATE UNIQUE INDEX tskIDX ON Tasks(TaskID, FKProjectID, FKUserID);
CREATE UNIQUE INDEX usrIDX ON Users(UserID);

INSERT INTO Users (UserID, Name, Role)
VALUES (1, "Justen Mehl", "Developer");

INSERT INTO Users (UserID, Name, Role)
VALUES (2, "Gregory Lane", "Developer");

INSERT INTO Users (UserID, Name, Role)
VALUES (3, "Sean Wickers", "Developer");

INSERT INTO Users (UserID, Name, Role)
VALUES (4, "Jason Willis", "Project Manager");

INSERT INTO Users (UserID, Name, Role)
VALUES (5, "David Thatcher", "Team Lead");

INSERT INTO Projects(ProjectID, ProjectName, ProjectAssignedTo,
ProjectDue, ProjectPriority, ProjectSummary)
VALUES(1, "TestProject", "Jason Willis", "2018-05-06",1,
	"This is a test project");

INSERT INTO Tasks(TaskID, FKProjectID, FKUserID,
TaskName, TaskDateCreated, TaskDateEnded, TaskPriority,
TaskSummary)
VALUES(1,1,2,"Develop SQL Script", "2018-03-19",NULL,1,"Build SQL script for DB");

INSERT INTO Projects(ProjectID, ProjectName, ProjectAssignedTo, 
ProjectDue, ProjectPriority, ProjectSummary)
VALUES(1, "Test Project", "Jason Willis", "2018-05-06", 1, "Capstone project for CMSC495");

INSERT INTO Tasks(TaskID, FKProjectID, FKUserID, Taskname, TaskDateCreated, TaskDateEnded, 
TaskPriority, TaskSummary)
VALUES(1,1,2,"Build SQL Script", "2018-03-19", NULL,1,"Test task for sql script");

