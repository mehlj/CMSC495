-- Create local instance of the databse

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
TaskDateEnded DATE NOT NULL,
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