import java.util.*;
class Node{
    int key;
    Node left;
    Node right;
    Node(int k){
        this.key=k;
    }
}

public class Main
{
    public static int maxlevel=0;
    public static void inorder(Node node){
        if(node==null){
            return;
        }
        inorder(node.left);
        System.out.print(node.key+" "  );
        inorder(node.right);
    }
    public static int size(Node node){
        if(node==null){
                return 0;
            }
            return 1+size(node.left)+size(node.right);
        }
        public static int max(Node node){
            if(node==null){
                return Integer.MIN_VALUE;
            }
            return Math.max(node.key,Math.max(max(node.left),max(node.right)));
        }
        
        public static void printleft(Node node,int level){
            if(node==null){
                return;
            }
            if(maxlevel<level){
                System.out.print(node.key+" ");
                maxlevel=level;
            }
            printleft(node.left,level+1);
            printleft(node.right,level+1);
        }
        public static boolean childrensum(Node node){
            if(node==null){
                return true;
            }
            if(node.left==null && node.right==null){{
                return true;
            }}
            int sum=0;
            if(node.left!=null){
                sum=sum+node.left.key;
            }
            if(node.right!=null){
                sum=sum+node.right.key;
            }
            return (sum==node.key)&&childrensum(node.left)&&childrensum(node.right);
        }
        public static int height(Node node){
            if(node ==null){
                return 0;
            }
            return 1+Math.max(height(node.left),height(node.right));
        }
        public static boolean checkbalanced(Node node){
            if(node==null){
                return true;
            }
            boolean temp=Math.abs(height(node.left)-height(node.right))<=1;
            return temp&&checkbalanced(node.left)&&checkbalanced(node.right);
        }
        public static int maxWidth(Node node){
            if(node==null){
                return 0;
            }
            Queue<Node> q=new LinkedList<Node>();
            q.add(node);
            int res=0;
            while(!q.isEmpty()){
                int count=q.size();
                res=Math.max(count,res);
                for(int i=0;i<count;i++){
                    Node curr=q.poll();
                    if(curr.left!=null){
                        q.add(curr.left);
                    }
                    if(curr.right!=null){
                        q.add(curr.right);
                    }
                }
            }
            return res;
        }
        static int preIndex=0;
        public static Node maketree(int[] in,int[] pre,int is,int ie){
            if(is>ie){
                return null;
            }
            Node root=new Node(preIndex++);
            int search=pre[preIndex];
            int inIndex=0;
            for(int i=0;i<in.length;i++){
                if(in[i]==search){
                    inIndex=i;
                    break;
                }
            }
            root.left=maketree(in,pre,is,inIndex-1);
            root.right=maketree(in,pre,inIndex+1,ie);
            return root;
        }
        public static void printSpiral(Node node){
            Queue<Node> q=new LinkedList<>();
            Stack<Node> s=new Stack<>();
            q.add(node);
            boolean result=true;
            while(!q.isEmpty()){
                int count=q.size();
                for(int i=0;i<count;i++){
                    Node out=q.poll();
                    if(result){
                        s.push(out);
                    }else{
                        System.out.print(out.key+" ");    
                        
                    }
                    
                    if(out.left!=null){
                        q.add(out.left);
                    }
                    if(out.right!=null){
                        q.add(out.right);
                    }
                }
                if(result){
                    while(!s.isEmpty()){
                        Node out=s.pop();
                        System.out.print(out.key+" ");
                    }
                }
                result=!result;
            }
        }
        public static int diameter(Node node){
            if(node==null){
                return 0;
            }
             int left=0;
            if(node.left!=null){
                 left=height(node.left);    
            }
            int right=0;
            if(node.right!=null){
                 right=height(node.right);
            }
            int diameter=1+left+right;
            return Math.max(diameter,Math.max(diameter(node.left),diameter(node.right)));
        }
        public static boolean pathExist(Node node,ArrayList<Node> arr,int p){
            if(node==null){
                return false;
            }
            arr.add(node);
            if(node.key==p){
                return true;
            }
            if(pathExist(node.left,arr,p) || pathExist(node.right,arr,p)){
                return true;
            }
            arr.remove(arr.size()-1);
            return false;
            
        }
        public static int lca(Node node,int n1,int n2){
            ArrayList<Node> path1=new ArrayList<>();
            ArrayList<Node> path2=new ArrayList<>();
            if(pathExist(node,path1,n1)==false || pathExist(node,path2,n2)==false){
                return -1;
            }
            for(int i=0;i<path1.size()-1 && i<path2.size()-1;i++){
                if(path1.get(i+1).key!=path2.get(i+1).key){
                    return path1.get(i).key;
                    
                }
            }
            return -1;
        }
            
        public static int count(Node node){
            if(node ==null){
                return 0;
            }
            Node curr=node;
            int left=0;
            while(curr.left!=null){
                curr=curr.left;
                left++;
            }
            int rh=0;
            curr=node;
            while(curr.right!=null){
                curr=curr.right;
                rh++;
            }
            if(left==rh){
                return (int)Math.pow(2,rh)-1;
            }else{
                return 1+count(node.left)+count(node.right);
            }
        }
    	public static void main(String[] args) {
             Node node=new Node(30);
            node.left=new Node(20);
            node.right=new Node(10);
            node.right.right=new Node(50);
            node.right.left=new Node(60);
            node.left.right=new Node(80);
            inorder(node);
            System.out.println();
            System.out.println(size(node));
            System.out.println(max(node));
            printleft((node),1);
            System.out.println();
            System.out.println(childrensum(node));
            System.out.println(height(node));
            System.out.println(checkbalanced(node));
            System.out.println(maxWidth(node));
            printSpiral(node);
            System.out.println();
            System.out.println(diameter(node));
            System.out.println(lca(node,80,60));
            System.out.println(count(node));
	}
}
