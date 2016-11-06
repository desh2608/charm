import scala.io.Source
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ArrayBuffer
import scala.language.postfixOps

object Charm {
   def main(args: Array[String]) {
   		print("Enter minimum threshold : " )
      	var minsup = scala.io.StdIn.readInt()
      	val items = scala.collection.mutable.Map[String,ArrayBuffer[Int]]()
      	var transaction:String = ""
      	var i = 1
      	for(line <- Source.fromFile("./data/Chess.csv").getLines()) { 
        	transaction = line.trim()
        	for(item <- transaction.split(",")){
        		 if(items.contains(item))
        		 	items.update(item,items(item) :+ i)
        		 else
        			items += item -> ArrayBuffer(i)
        	}
        	i = i+1
      	}
      	println("Data loaded.")

      	  		
      	var C:ArrayBuffer[(ArrayBuffer[String], ArrayBuffer[Int])] = charm(items,minsup)
        for(i<-0 to (C.length-1))
        {
          println(C(i)._1,(C(i)._2).length)
        }

   }
   
   def charm( D:scala.collection.mutable.Map[String,ArrayBuffer[Int]], minsup:Int ) : ArrayBuffer[(ArrayBuffer[String], ArrayBuffer[Int])] = {
      	var P = new ArrayBuffer[(ArrayBuffer[String], ArrayBuffer[Int])]()
      	for ((key,value) <- D){
      		if (value.size >= minsup){
      			P :+= ArrayBuffer(key)->value
      		}
      	}
        P = P.sortBy(+_._2.size)
        var C = new ArrayBuffer[(ArrayBuffer[String], ArrayBuffer[Int])]()
        
        C=charmExtend (P,C,minsup)
        return C
   }

   def charmExtend(P:ArrayBuffer[(ArrayBuffer[String], ArrayBuffer[Int])],C:ArrayBuffer[(ArrayBuffer[String], ArrayBuffer[Int])],minsup:Int) : 
   ArrayBuffer[(ArrayBuffer[String], ArrayBuffer[Int])] = {
   	  
      var P1 : ArrayBuffer[(ArrayBuffer[String], ArrayBuffer[Int])] =P
      var C1 : ArrayBuffer[(ArrayBuffer[String], ArrayBuffer[Int])] =C
      var P_i = new ArrayBuffer[(ArrayBuffer[String], ArrayBuffer[Int])]()
     
      if(P1.length<=0)
      return C1
      for (i <- 0 to (P1.length - 1))
      {
        
          for (j<-i+1 to (P1.length-1))
          {
            var x_i:ArrayBuffer[String]=P1(i)._1
            var t_x_i:ArrayBuffer[Int]=P1(i)._2
            var x_j:ArrayBuffer[String]=P1(j)._1
            var t_x_j:ArrayBuffer[Int]=P1(j)._2

            var x_ij=new ArrayBuffer[String]()
            var t_x_ij=new ArrayBuffer[Int]()

            x_ij=x_i.union(x_j).distinct
            t_x_ij=t_x_i.intersect(t_x_j).distinct
            if(t_x_ij.length>=minsup)
            {
               if(t_x_ij.length==t_x_i.length && t_x_ij.length==t_x_j.length)
               {
                  P1(i)=(x_ij,t_x_ij)
                  for(k<-0 to (P_i.length-1))
                  {
                    var x_temp=P_i(k)._1
                    var t_x_temp=P_i(k)._2
                    x_temp=x_temp.union(x_j).distinct
                    P_i(k)=(x_temp,t_x_temp)
                  }
                  //P.remove(j)
               }
              else 
              {
                    if(t_x_ij.length==t_x_i.length)
                    {
                      P1(i)=(x_ij,t_x_ij)
                      for(k<-0 to (P_i.length-1))
                      {
                        var x_temp=P_i(k)._1
                        var t_x_temp=P_i(k)._2
                        x_temp=x_temp.union(x_j).distinct
                        P_i(k)=(x_temp, t_x_temp)
                      }
                    }

                    else
                    {
                      P_i :+=(x_ij, t_x_ij)
                    }
              }

          }
        }
      if(P_i.length>0)
      {
        C1=charmExtend(P_i,C1,minsup)
      }

      var flag:Int=1
      var x_i:ArrayBuffer[String]=P1(i)._1
      var t_x_i:ArrayBuffer[Int]=P1(i)._2
      if(C1.length>0)
      {

        for(j<-0 to C1.length-1)
        {
              
              var x_j:ArrayBuffer[String]=C1(j)._1
              var t_x_j:ArrayBuffer[Int]=C1(j)._2

              var x_ij=new ArrayBuffer[String]()
              var t_x_ij=new ArrayBuffer[Int]()
              
              x_ij=x_i.intersect(x_j).distinct
              t_x_ij=t_x_i.intersect(t_x_j).distinct
              if(x_ij.length==x_i.length && t_x_ij.length==t_x_i.length && t_x_ij.length==t_x_j.length)
              {
                flag=0
              }
             
        }
      }

       if(flag==1)
            {
              C1 :+=(x_i, t_x_i)
            }    
    }

    return C1
  }
}

      