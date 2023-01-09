import tkinter as tk
import mysql.connector
from tkinter import ttk
import threading
rows1 = []
rows2 = []
rows3 = []

def join():
    global rows1
    global rows2
    global rows3
    ans = rows1 + rows2 + rows3
    for i in ans:
        finalTree.insert("", "end", values=i)

def sortOne(a, res, pos):
    a.sort(key=lambda x: x[0])
    res[pos] = a

def startSort():
    global rows1
    global rows2
    global rows3
    result = [None] * 3
    thread1 = threading.Thread(target=sortOne, args=(rows1, result, 0))
    thread2 = threading.Thread(target=sortOne, args=(rows2, result, 1))
    thread3 = threading.Thread(target=sortOne, args=(rows3, result, 2))
    thread1.start()
    thread2.start()
    thread3.start()
    thread1.join()
    thread2.join()
    thread3.join()
    rows1 = result[0]
    rows2 = result[1]
    rows3 = result[2]
    for i in rows1:
        sorted1.insert("", "end", values=i)
    for i in rows2:
        sorted2.insert("", "end", values=i)
    for i in rows3:
        sorted3.insert("", "end", values=i)

def startPartition(result):
    global rows1
    global rows2
    global rows3
    for i in range(0, len(result)):
        if result[i][0] < 11:
            partition1.insert("", "end", values=result[i])
            rows1.append(result[i])
        elif result[i][0] < 21:
            partition2.insert("", "end", values=result[i])
            rows2.append(result[i])
        else:
            partition3.insert("", "end", values=result[i])
            rows3.append(result[i])

root = tk.Tk()

root.geometry("1500x800")
root.title("Parallel sort")
# Main Tree
maintree = ttk.Treeview(root, show='headings', height=5)
maintree.place(x=20, y=20)
conn = mysql.connector.connect(
host='localhost', user='root', password='root', database='sorting')
cursor = conn.cursor()
cursor.execute("SELECT * FROM employee")
field_names = [i[0] for i in cursor.description]
maintree["columns"] = field_names
for i in field_names:
    maintree.heading(i, text=i)
rows = cursor.fetchall()
for row in rows:
    maintree.insert("", "end", values=row)
# partition Button
startbtn = tk.Button(root, text="Partition", command=lambda: startPartition(
rows), bg='#3DB2FF', fg='white', font=('monospace', 12, 'bold'))
startbtn.place(x=1200, y=50, width=100, height=50)
# Partition 1
partition1label = tk.Label(root, text="Disk 1",
bg='#3DB2FF', fg='white', font=('monospace', 12, 'bold'))
partition1label.place(x=20, y=170)
partition1 = ttk.Treeview(root, show='headings', height=5)
partition1.place(x=20, y=200)
partition1["columns"] = field_names
for i in field_names:
    partition1.column(i, anchor='center', width=80)
    partition1.heading(i, text=i)

# Partition 2
partition2label = tk.Label(root, text="Disk 2",
bg='#3DB2FF', fg='white', font=('monospace', 12, 'bold'))
partition2label.place(x=330, y=170)
partition2 = ttk.Treeview(root, show='headings', height=5)

partition2.place(x=330, y=200)
partition2["columns"] = field_names
for i in field_names:
    partition2.column(i, anchor='center', width=80)
    partition2.heading(i, text=i)
# Partition 3
partition3label = tk.Label(root, text="Disk 3",
bg='#3DB2FF', fg='white', font=('monospace', 12, 'bold'))
partition3label.place(x=640, y=170)
partition3 = ttk.Treeview(root, show='headings', height=5)
partition3.place(x=640, y=200)
partition3["columns"] = field_names
for i in field_names:
    partition3.column(i, anchor='center', width=80)
    partition3.heading(i, text=i)
# sort button
tk.Label(root, text="Sorted Partitions",
bg='#3DB2FF', fg='white', font=('monospace', 12, 'bold')).place(x=400, y=370)
sortButton = tk.Button(root, text="Sort", command=startSort,
bg='#3DB2FF', fg='white', font=('monospace', 12, 'bold'))
sortButton.place(x=1200, y=250, width=100, height=50)

# Partition 1 after sort
sorted1 = ttk.Treeview(root, show='headings', height=5)
sorted1.place(x=20, y=400)
sorted1["columns"] = field_names
for i in field_names:
    sorted1.column(i, anchor='center', width=80)
    sorted1.heading(i, text=i)
# Partition 2 after sort
sorted2 = ttk.Treeview(root, show='headings', height=5)
sorted2.place(x=330, y=400)
sorted2["columns"] = field_names
for i in field_names:
    sorted2.column(i, anchor='center', width=80)
    sorted2.heading(i, text=i)

# Partition 3 after sort
sorted3 = ttk.Treeview(root, show='headings', height=5)
sorted3.place(x=640, y=400)
sorted3["columns"] = field_names
for i in field_names:
    sorted3.column(i, anchor='center', width=80)
    sorted3.heading(i, text=i)
# join button
joinButton = tk.Button(root, text="Join", command=join,
bg='#3DB2FF', fg='white', font=('monospace', 12, 'bold'))
joinButton.place(x=1200, y=450, width=100, height=50)
# Final Table
tk.Label(root, text="Final Result after joining sorted partitions",
bg='#3DB2FF', fg='white', font=('monospace', 12, 'bold')).place(x=540, y=570)
finalTree = ttk.Treeview(root, show='headings', height=5)
finalTree.place(x=20, y=600)
finalTree["columns"] = field_names
for i in field_names:
    finalTree.heading(i, text=i)
root.mainloop()

# Database:-------------



# create database sorting;

# use sorting;

# create table employee(
# emp_id int,
# fname varchar(20),
# lname varchar(20),
# age int,
# salary int
# );

# create table disk1(
# emp_id int,
# fname varchar(20),
# lname varchar(20),
# age int,
# salary int
# );

# create table disk2(
# emp_id int,
# fname varchar(20),
# lname varchar(20),
# age int,
# salary int
# );

# create table disk3(
# emp_id int,
# fname varchar(20),
# lname varchar(20),
# age int,
# salary int
# );

# create table disk4(
# emp_id int,
# fname varchar(20),
# lname varchar(20),
# age int,
# salary int
# );


# insert into employee values
# (1,'Vaibhav','Bhus',22,25000),
# (2,'Vaibhav','Bhus',22,25000),
# (3,'Vaibhav','Bhus',22,25000),
# (4,'Vaibhav','Bhus',22,25000),
# (5,'Vaibhav','Bhus',22,25000),
# (6,'Vaibhav','Bhus',22,25000),
# (7,'Vaibhav','Bhus',22,25000),
# (8,'Vaibhav','Bhus',22,25000),
# (9,'Vaibhav','Bhus',22,25000),
# (12,'Vaibhav','Bhus',22,25000),
# (22,'Vaibhav','Bhus',22,25000),
# (33,'Vaibhav','Bhus',22,25000),
# (14,'Vaibhav','Bhus',22,25000),
# (15,'Vaibhav','Bhus',22,25000),
# (26,'Vaibhav','Bhus',22,25000),
# (37,'Vaibhav','Bhus',22,25000),
# (17,'Vaibhav','Bhus',22,25000),
# (30,'Vaibhav','Bhus',22,25000),
# (21,'Vaibhav','Bhus',22,25000);

