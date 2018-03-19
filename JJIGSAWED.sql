-- Create local instance of the databse

CREATE DATABASE CMSC495;

USE CMSC495;

CREATE TABLE Projects(ProjectID INT NOT NULL, 
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
FOREIGN KEY (FKUserID) references Users(UserID), -- Will fail until Users table is created
PRIMARY KEY(TaskID)
);

CREATE UNIQUE INDEX prjIDX ON Projects(ProjectID);
CREATE UNIQUE INDEX tskIDX ON Tasks(TaskID, FKProjectID, FKUserID);

