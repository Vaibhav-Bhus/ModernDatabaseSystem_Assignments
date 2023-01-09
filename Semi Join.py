import tkinter as tk
from tkinter import ttk
from tkinter.messagebox import showinfo
from tkinter import Toplevel, messagebox, Text
from prettytable import PrettyTable
import mysql.connector
list1 = []
global connection, cursor


def submit2():
    global cursor
    ttk.Label(window, text=" TEMP2 = (r2 join temp1): ",
              font=("Times New Roman", 13)).grid(column=3,
                                                 row=5, padx=10, pady=20)
    cursor.execute("select * from temp2")
    record = cursor.fetchall()
    i = 7
    e = ttk.Label(window, width=27, text=" Id S1 S2 S3 ",
                  borderwidth=2, relief='solid', anchor="w")
    e.grid(row=i, column=3, padx=60)

    i += 1
    for row in record:
        e = ttk.Label(window, width=27, text=str(row[0])+" "+str(row[1])+" "+str(row[2]) + " "+str(row[3]),
                      borderwidth=2, relief='solid', anchor="w")
        e.grid(row=i, column=3, padx=60)
        i += 1
    ttk.Label(window, text=" r1 join temp2: ",
              font=("Times New Roman", 13)).grid(column=3,
                                                 row=i+2, padx=10, pady=20)
    qq = "drop table if exists semijoin"
    cursor.execute(qq)
    qq = "create table semijoin select temp2.studid, temp2.Subj1, temp2.Subj2, temp2.Subj3, student.branch, student.name from temp2 inner join student on temp2.studid = student.studid"
    cursor.execute(qq)
    qq = "select * from semijoin"
    cursor.execute(qq)
    record = cursor.fetchall()
    i = 18
    ttk.Label(window, width=50, text="ID S1 S2 S3 Branch NAME", borderwidth=2,
              relief='solid', anchor="w").grid(row=i, column=3, padx=60)
    i += 1
    for row in record:
        e = ttk.Label(window, width=50, text=str(row[0])+" "*(5-len(str(row[0]))) +
                      str(row[1])+" "+str(row[2])+" "+str(row[3])+" "+str(row[4]) + " "+str(row[5]), borderwidth=2, relief='solid', anchor="w")
        e.grid(row=i, column=3, padx=60)
        i += 1
            

def submit1():

    addroot = Toplevel(master=window)
    addroot.grab_set()  # multiple times same window shld not open onclick and iska operation hone ke baad he next window shld open so this is used
    addroot.geometry('800x800')
    addroot.title('semi join site 2')
    addroot.config(bg='white')
    ttk.Label(addroot, text=" semi join ",
              background='black', foreground="white",
              font=("Times New Roman", 25)).grid(row=0, column=5, pady=20)
    ttk.Label(addroot, text=" Site 2 ",
              font=("Times New Roman", 17)).grid(row=1, column=5, pady=20)
    ttk.Label(addroot, text=" TEMP 1 RECIEVED FROM SITE 1 ",
              font=("Times New Roman", 13)).grid(row=2, column=5, pady=20)

    try:
        global connection
        global list1
        global cursor
        i = 18
        connection = mysql.connector.connect(host='localhost',
                                             database='semijoin',
                                             user="root",
                                             password='root')
        if connection.is_connected():
            db_Info = connection.get_server_info()
            print("Connected to MySQL Server version ", db_Info)
            cursor = connection.cursor()
            cursor.execute("select database();")
            record = cursor.fetchone()
            print("You're connected to database: ", record)
            cursor.execute('drop table if exists temp1')
            qq = "create table semijoin.temp1 select (studid) from semijoin.student"
            cursor.execute(qq)
        ttk.Label(addroot, text=" TEMP 1 RECIEVED FROM SITE 1 ",
                  font=("Times New Roman", 13)).grid(row=2, column=5, pady=20)

        cursor.execute("select * from temp1")
        record = cursor.fetchall()
        ttk.Label(addroot, text="Studid", font=("Times New Roman", 15)).grid(
            row=17, pady=20, column=3)
        for row in record:
            for j in range(len(row)):
                e = ttk.Label(addroot, width=25, text=row[j],
                              borderwidth=2, relief='solid', anchor="w")
                e.grid(row=i, column=3, padx=60)
            i += 1
        qq = "drop table if exists temp2"
        cursor.execute(qq)
        qq = "create table semijoin.temp2 select marks.studid,student.fname, student.lname, marks.Subj1,marks.Subj2,marks.Subj3 from marks inner join student on marks.studid=student.studid"
        cursor.execute(qq)
    except Exception as e:
        print("Error while connecting to MySQL", e)

    ttk.Label(addroot, text="Send r2 join temp1 to site1", font=("Times New Roman", 15)).grid(
        row=i+1, pady=20, column=3)

    ttk.Button(addroot, text='Submit', command=submit2).grid(column=9,
                                                             row=i+1, padx=20)


window = tk.Tk()
window.title('semi join site 1')
window.geometry('1000x800+0+0')
# label text for title
ttk.Label(window, text=" Semi Join ",
          background='black', foreground="white",
          font=("Times New Roman", 25)).grid(row=0, column=5, pady=20)
ttk.Label(window, text=" Site 1 ",
          font=("Times New Roman", 17)).grid(row=1, column=5, pady=20)
ttk.Label(window, text="Send temp1 to site 2:",
          font=("Times New Roman", 13)).grid(column=3,
                                             row=3, padx=10, pady=20)
ttk.Button(window, text='Submit', command=submit1).grid(column=9,
                                                        row=3, padx=10)

window.mainloop()









# DATABASE------

# create database semijoin;

# use semijoin;



# create table student(
# studid int primary key,
# fname varchar(20),
# lname varchar(20)
# );

# create table marks(
# studid int ,
# Subj1 int,
# Subj2 int,
# Subj3 int,
# CONSTRAINT FK_PersonOrder FOREIGN KEY (studid)
#     REFERENCES student(studid));
    
    
    


# insert into student values
# (1,'Vaibhav','Bhus'),
# (2,'Ankur','Ajanalkr'),
# (3,'Samiksha','Purwat'),
# (4,'Ziya','Aurade'),
# (5,'Yash','Bhus'),
# (6,'Samruddhi','Purwat'),
# (7,'Yogita','Konapure');



# insert into marks values
# (1,98,87,86),
# (2,52,75,96),
# (3,98,45,65),
# (4,98,87,96),
# (5,68,87,76);






























