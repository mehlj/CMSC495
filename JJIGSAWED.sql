-- Create local instance of the database

CREATE DATABASE CMSC495;

USE CMSC495;

CREATE TABLE Users(
UserID INT NOT NULL,
Name VARCHAR(100) NOT NULL,
Role VARCHAR(25) NOT NULL,
Inactive INT NOT NULL,
PRIMARY KEY(UserID)
);

CREATE TABLE Projects(
ProjectID INT NOT NULL, 
ProjectName VARCHAR(200) NOT NULL,
ProjectDue VARCHAR(100) NOT NULL,
ProjectPriority INT NOT NULL,
ProjectSummary VARCHAR(2000) NOT NULL,
PRIMARY KEY (ProjectID)
);

CREATE TABLE Tasks(
TaskID INT NOT NULL,
FKProjectID INT NOT NULL,
FKUserID INT NOT NULL,
TaskName VARCHAR(200) NOT NULL,
TaskDateCreated VARCHAR(100) NOT NULL,
TaskDateEnded VARCHAR(100),
TaskPriority INT NOT NULL,
TaskSummary VARCHAR(2000) NOT NULL,
FOREIGN KEY (FKProjectID) references Projects(ProjectID) ON DELETE CASCADE,
FOREIGN KEY (FKUserID) references Users(UserID) ON DELETE CASCADE,
PRIMARY KEY(TaskID)
);

CREATE UNIQUE INDEX prjIDX ON Projects(ProjectID);
CREATE UNIQUE INDEX tskIDX ON Tasks(TaskID, FKProjectID, FKUserID);
CREATE UNIQUE INDEX usrIDX ON Users(UserID);

INSERT INTO Users (UserID, Name, Role, Inactive)
VALUES (1, "Justen Mehl", "Developer", 0);

INSERT INTO Users (UserID, Name, Role, Inactive)
VALUES (2, "Gregory Lane", "Developer", 0);

INSERT INTO Users (UserID, Name, Role, Inactive)
VALUES (3, "Sean Wickers", "Developer", 0);

INSERT INTO Users (UserID, Name, Role, Inactive)
VALUES (4, "Jason Willis", "Project Manager", 0);

INSERT INTO Users (UserID, Name, Role, Inactive)
VALUES (5, "David Thatcher", "Team Lead", 0);

INSERT INTO Projects(ProjectID, ProjectName, ProjectAssignedTo, 
ProjectDue, ProjectPriority, ProjectSummary)
VALUES(1, "Test Project", "2018-05-06", 1, "Capstone project for CMSC495");

INSERT INTO Tasks(TaskID, FKProjectID, FKUserID, Taskname, TaskDateCreated, TaskDateEnded, 
TaskPriority, TaskSummary)
VALUES(1,1,2,"Build SQL Script", "2018-03-19", NULL,1,"Test task for sql script");
