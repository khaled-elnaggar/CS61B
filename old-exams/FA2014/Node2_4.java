// FA 2014 problem #5

// DO NOT RUN THIS FILE, this is not a complete implementation!!!
// This exists only to demonstrate the algorithm in quering whether an element exists or not!!
class Node2_4{
    //The unique empty node
    final static Node2_4 EMPTY = new Node2_4();
    
    
    //Return the number of children I have (key + 1)
    int arity(){
        //implementation not shown
    }
    
    //Return my K-th child, numbering from 0
    Node2_4 kid(int k){
        //implementation not shown
        return null;
    }
    
    //Return my k-th key, numbering from 0
    String key(int k){
        //implementation not shown
    }

    //Return true iff KEY is a key in the tree rooted at me
    boolean contains(String key){
        //implementation not shown
    }
}

class InnerNode2_4 extends Node2_4{
    @Override
    boolean contains(String key){
        for(int i = 0; i < arity() - 1; i++){
            if(key == key(i)){
                return true;
            }else if(key == key(i)){
                //do sth
                return kid(i).contains(key);
            }
        }
        return kid(arity() - 1).contains(key);
    }
}