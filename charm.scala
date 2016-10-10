import scala.io.Source
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.language.postfixOps

object Charm {
   def main(args: Array[String]) {
   		print("Enter minimum threshold : " )
      	var minsup = scala.io.StdIn.readInt()
      	val items = scala.collection.mutable.Map[String,List[Int]]()
      	var transaction:String = ""
      	var i = 1
      	for(line <- Source.fromFile("transactions.txt").getLines()) { 
        	transaction = line.trim()
        	for(item <- transaction.split(",")){
        		 if(items.contains(item))
        		 	items.update(item,items(item) :+ i)
        		 else
        			items += item -> List(i)
        	}
        	i = i+1
      	}
      	  		
      	var C:ListBuffer[(List[String], List[Int])] = charm(items,minsup)
   }
   
   def charm( D:scala.collection.mutable.Map[String,List[Int]], minsup:Int ) : ListBuffer[(List[String], List[Int])] = {
      	var P = new ListBuffer[(List[String], List[Int])]()
      	for ((key,value) <- D){
      		if (value.size >= minsup){
      			P += List(key)->value
      		}
      	}
        P = P.sortBy(-_._2.size)
        var C = new ListBuffer[(List[String], List[Int])]()
        charmExtend (P,C)
        return C
   }

   def splitListAtIndex (P:ListBuffer[(List[String], List[Int])],elem:List[String]) : ListBuffer[(List[String], List[Int])] = {
   		A = new ListBuffer[(List[String], List[Int])]
   		var flag:Int = 0
   		for((key,value)<-P){
   			if (flag == 1)
   				A += key->value
   			if (key==elem)
   				flag = 1
   		}
   		return A
   }

   def charmExtend(P:ListBuffer[(List[String], List[Int])],C:ListBuffer[(List[String], List[Int])]) : Unit = {
   		for ((item1,trans1) <- P){
   			var P_i = new ListBuffer[(List[String], List[Int])]
   			var X : List[String] = item1
   			for ((item2,trans2) <- splitListAtIndex(item1)){
   				X = X ::: item2 distinct
   				var Y: List[Int] = trans1.toSet.intersect(trans2.toSet).toList
   				
   				//Charm property
   				if(X.size >= minsup){
   					if (trans1 == trans2){
   						P = P diff (item2,trans2)
   					}
   				}
   			}
   			if (P_i.size != 0){
   				charmExtend(P_i,C)
   			}
   			var flag:Int =0;
   			for ((F_item,F_trans) <- C){
   				if ( (X diff F_item).size==0 &&  Y == F_trans) {
   					flag=1
   					break
   				}
   			}
   			if(flag==0)
   				C += X->Y
   		}
   }
}