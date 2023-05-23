public class IntList{
   public int first;
   public IntList rest;
   public IntList(int first,IntList rest){
      this.first = first;
      this.rest = rest;
   }
   public static IntList square(IntList L){
      if(L == null){
         return L;
      }
      IntList TempList = new IntList(L.first*L.first,null);
      IntList ListBegin = TempList;
      while(L.rest != null){
         int sum=L.rest.first*L.rest.first;
         TempList.rest = new IntList(sum,null);
         L=L.rest;
         TempList=TempList.rest;
         //L=L.rest;
         //TempList=new IntList(L.first*L.first, TempList);
      }
      return ListBegin;
   }
   
   public static IntList squareDestructive(IntList L){
      if (L==null){
         return L;
      }
      L.first = L.first*L.first;
      L.rest = squareDestructive(L.rest);
      return L;
   }
   public static void main(String[] args){
      IntList A= new IntList(10,null);
      A=new IntList(20,A);
      A=new IntList(40,A);
      IntList B=square(A);
      IntList C=squareDestructive(A);
   }
}