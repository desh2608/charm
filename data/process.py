import csv

# m = ['bkblk','bknwy','bkon8','bkona','bkspr','bkxbq','bkxcr','bkxwp','blxwp','bxqsq','cntxt','dsopp','dwipd','hdchk','katri','mulch','qxmsq','r2ar8','reskd','reskr','rimmx','rkxwp','rxmsq','simpl','skach','skewr','skrxp','spcop','stlmt','thrsk','wkcti','wkna8','wknck','wkovl','wkpos','wtoeg','win']
# transactions = []
# with open('chess.data','r') as f:
# 	for line in f:
# 		transaction = []
# 		items = line.strip().split(',')
# 		for i,x in enumerate(items):
# 			if x in ('t','g','w','won'):
# 				transaction.append(m[i])
# 		transactions.append(transaction)

# print 'Rows read...'

# with open("Chess.csv", "wb") as f:
# 	writer = csv.writer(f)
# 	writer.writerows(transactions)


# print 'Processing complete.'

# m = ['a1','a2','a3','a4','a5','a6','b1','b2','b3','b4','b5','b6','c1','c2','c3','c4','c5','c6','d1','d2','d3','d4','d5','d6','e1','e2','e3','e4','e5','e6','f1','f2','f3','f4','f5','f6','g1','g2','g3','g4','g5','g6','win']
# transactions = []
# with open('connect.data','r') as f:
# 	for line in f:
# 		transaction = []
# 		items = line.strip().split(',')
# 		for i,x in enumerate(items):
# 			if x in ('x','win'):
# 				transaction.append(m[i])
# 		transactions.append(transaction)

# print 'Rows read...'

# with open("Connect.csv", "wb") as f:
# 	writer = csv.writer(f)
# 	writer.writerows(transactions)


# print 'Processing complete.'

m = 
transactions = []
with open('connect.data','r') as f:
	for line in f:
		transaction = []
		items = line.strip().split(',')
		for i,x in enumerate(items):
			if x in ('x','win'):
				transaction.append(m[i])
		transactions.append(transaction)

print 'Rows read...'

with open("Connect.csv", "wb") as f:
	writer = csv.writer(f)
	writer.writerows(transactions)


print 'Processing complete.'