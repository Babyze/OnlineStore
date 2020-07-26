CREATE DATABASE DHA_Assignment_1
GO
USE DHA_Assignment_1
GO
CREATE TABLE tblUser(
	uUsername varchar(20) primary key,
	uPassword varchar(30) not null,
	uFullName nvarchar(50),
	uAddress nvarchar(200),
	isAdmin bit
)
CREATE TABLE tblProduct(
	pId varchar(10) primary key,
	pName nvarchar(50) not null
)
CREATE TABLE tblOrder(
	oId int primary key identity(1,1),
	uUsername varchar(20) FOREIGN KEY REFERENCES tblUser(uUsername),
	oFullname nvarchar(50) not null,
	oAddress nvarchar(200) not null
)
CREATE TABLE tblOrderDetails(
	odId int primary key identity(1,1),
	pId varchar(10) FOREIGN KEY REFERENCES tblProduct(pId),
	quantity int,
	oId int FOREIGN KEY REFERENCES tblOrder(oId)
)

INSERT INTO tblUser VALUES('admin','admin','Administrator','FPT University, Khu Cong Nghe Cao, Quan 9, Ho Chi Minh',1)
INSERT INTO tblUser VALUES('user','user','Normal User','G2-22, Dn5, Tan Hung Thuan, Quan 12, Ho Chi Minh',0)
INSERT INTO tblUser VALUES('huynhanh','huynhanh','Huynh Anh','Thi Xa Di An, Binh Duong',1)
INSERT INTO tblUser VALUES('babyze','babyze','Babyze Name','15 Dien Bien Phu, Quan 3, Ho Chi Minh',0)

INSERT INTO tblProduct VALUES('JW01','Head First Servlet and JSP')
INSERT INTO tblProduct VALUES('JC01','Head First Java Core')
INSERT INTO tblProduct VALUES('PY01','Python Basic')
INSERT INTO tblProduct VALUES('C02','C# and Dot Net')
INSERT INTO tblProduct VALUES('JS03','JavaScript')