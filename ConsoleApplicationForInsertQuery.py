import sqlite3

coll = sqlite3.connect("data")
a = coll.cursor()
a.execute("Create Table if not exists College (name  text,PId int ,NoDept int , NoStud int)")

#a.execute("Insert into College values(?,?,?,?)", ("Samiksha", 1, 101, 50))

query = input()

b = a.execute(query)
d = a.execute("select * from College")
for i in d.fetchall():
    print(i)