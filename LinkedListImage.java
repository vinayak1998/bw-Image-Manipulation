import java.util.*;
import java.io.*;

class Node{

    protected int element;
    protected Node next;

    public Node(){
        element = 0;
        next = null;
    }

    public Node(int e , Node n){
        element = e;
        next = n;
    }

    public int getElement(){
        return element;
    }

    public void setElement(int d){
        element =d;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node m){
        next = m;
    }

}

class Linkedlist{

    public Node head;
    public Node tail;
    public int size;

    public Linkedlist(){
        head = null;
        tail = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int first(){
        return head.getElement();
    }

    public int last(){
        return tail.getElement();
    }

    public void addFirst(int e){
        size++;
        if (head == null) {
            head.setElement(e);
            head.setNext(null);
        }
        else{
            Node newNode = new Node(e,head);
            head = newNode;
        }
    }

    public void addLast(int e){

        Node newest = new Node(e,null);
        if(isEmpty()){
            head = newest;
            tail = head;
        }
        else{
            tail.setNext(newest);
            tail = newest;
        }
        size++;
    }

}

public class LinkedListImage implements CompressedImageInterface {

    public Linkedlist[] a;
    public int[] nobp;
    public int height;
    public int width;

    public LinkedListImage(String filename) {
        //you need to implement this
        //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        try {
            FileInputStream file = new FileInputStream(filename);
            Scanner x = new Scanner(file);

            height = x.nextInt();
            width = x.nextInt();
            Linkedlist[] comp = new Linkedlist[height + 1];
            int[] compx = new int[height];
            a = comp;
            nobp = compx;
            comp[0] = new Linkedlist();

            comp[0].addLast(height);
            comp[0].addLast(width);

            for (int i = 1; i < height + 1; i++) {

                comp[i] = new Linkedlist();
                Linkedlist compnew = new Linkedlist();

                for (int j = 0; j < width; j++) {

                    int b = x.nextInt();

                    if (b == 0) {
                        compx[i-1]++;
                        if (j == width - 1) {
                            compnew.addLast(j);

                            if (compnew.size() == 1) {
                                comp[i].addLast(compnew.first());
                                comp[i].addLast(compnew.first());
                            } else if (compnew.size() > 1) {
                                comp[i].addLast(compnew.first());
                                comp[i].addLast(compnew.last());
                            }
                            compnew = new Linkedlist();
                        } else {
                            compnew.addLast(j);
                        }
                    }
                    else {

                        if (compnew.size() == 1) {
                            comp[i].addLast(compnew.first());
                            comp[i].addLast(compnew.last());
                        } else if (compnew.size() > 1) {
                            comp[i].addLast(compnew.first());
                            comp[i].addLast(compnew.last());
                        } else {
                            continue;
                        }
                        compnew = new Linkedlist();

                    }
                }
                comp[i].addLast(-1);

            }

        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUNDDD!");
        }
    }   // DONE

    public LinkedListImage(boolean[][] grid, int w, int h) // DONE
    {
        height = h;
        width = w;
        Linkedlist[] comp = new Linkedlist[height + 1];
        int[] compx = new int[height];
        a = comp;
        nobp = compx;
        comp[0] = new Linkedlist();

        comp[0].addLast(height);
        comp[0].addLast(width);

        for (int i = 1; i < height + 1; i++) {

            comp[i] = new Linkedlist();
            Linkedlist compnew = new Linkedlist();

            for (int j = 0; j < width; j++) {

                int b;
                if (grid[i-1][j]) {
                    b=1;
                }
                else{
                    b=0;
                }

                if (b == 0) {
                    compx[i-1]++;
                    if (j == width - 1) {
                        compnew.addLast(j);

                        if (compnew.size() == 1) {
                            comp[i].addLast(compnew.first());
                            comp[i].addLast(compnew.first());
                        } else if (compnew.size() > 1) {
                            comp[i].addLast(compnew.first());
                            comp[i].addLast(compnew.last());
                        }
                        compnew = new Linkedlist();
                    } else {
                        compnew.addLast(j);
                    }
                }
                else {

                    if (compnew.size() == 1) {
                        comp[i].addLast(compnew.first());
                        comp[i].addLast(compnew.last());
                    } else if (compnew.size() > 1) {
                        comp[i].addLast(compnew.first());
                        comp[i].addLast(compnew.last());
                    } else {
                        continue;
                    }
                    compnew = new Linkedlist();

                }
            }
            comp[i].addLast(-1);

        }

    }

    public boolean getPixelValue(int x, int y) throws PixelOutOfBoundException
    {
		//you need to implement this
		//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        if (x<height && y<width && x>=0 && y>=0) {
            Node ptr = a[x + 1].head;
            int cntr = 1;
            for (int i = 0; i < a[x + 1].size() - 1; i++) {
                if (cntr == 1)
                    cntr--;
                else
                    cntr++;
                if (ptr.getElement() == y)
                    return false;
                else if (y < ptr.getElement() && cntr == 0) {
                    return true;
                } else if (y > ptr.getElement() && cntr == 0 && y <= (ptr.getNext()).getElement()) {
                    return false;
                } else if (y > ptr.getElement() && cntr == 1 && y < (ptr.getNext()).getElement()) {
                    return true;
                }
                ptr = ptr.getNext();
            }
            return true;
        }
        else{
            throw new PixelOutOfBoundException("Check the input");
        }
    }

    public void setPixelValue(int x, int y, boolean val) throws PixelOutOfBoundException
    {
        //you need to implement this
        //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        if (x<height && y<width && x>=0 && y>=0) {
            if (getPixelValue(x, y) == val) {
                return;
            }
            int[] arr = new int[width];
            Node ptr = a[x + 1].head;
            Node temp = new Node();
            int cntr = 2;
            if (val) {
                nobp[x]--;
            } else
                nobp[x]++;

            if (a[x + 1].size() > 1) {
                for (int i = 0; i < a[x + 1].size() - 1; i = i + 1) {
                    if (cntr == 1)
                        cntr--;
                    else if (cntr == 0)
                        cntr++;


                    if (cntr == 1) {
                        for (int j = temp.getElement() + 1; j < ptr.getElement(); j++) {
                            arr[j] = 1;
                        }
                    } else if (cntr == 0) {
                        for (int j = temp.getElement(); j <= ptr.getElement(); j++) {
                            arr[j] = 0;
                        }
                    } else {
                        for (int j = 0; j < ptr.getElement(); j++) {
                            arr[j] = 1;
                        }
                        cntr = 1;

                    }
                    temp = ptr;
                    ptr = ptr.getNext();

                    if (ptr.getElement() == -1) {
                        for (int j = temp.getElement() + 1; j < width; j++) {
                            arr[j] = 1;
                        }
                        break;
                    }


                }
            } else {
                for (int i = 0; i < width; i++) {
                    arr[i] = 1;
                }
            }

            if (val)
                arr[y] = 1;
            else
                arr[y] = 0;


            Linkedlist compnew = new Linkedlist();
            Linkedlist link = new Linkedlist();
            for (int j = 0; j < width; j++) {


                if (arr[j] == 0) {
                    //nobp[j]++;
                    if (j == width - 1) {
                        compnew.addLast(j);

                        if (compnew.size() == 1) {
                            link.addLast(compnew.first());
                            link.addLast(compnew.first());
                        } else if (compnew.size() > 1) {
                            link.addLast(compnew.first());
                            link.addLast(compnew.last());
                        }
                        compnew = new Linkedlist();
                    } else {
                        compnew.addLast(j);
                    }
                } else {

                    if (compnew.size() == 1) {
                        link.addLast(compnew.first());
                        link.addLast(compnew.last());
                    } else if (compnew.size() > 1) {
                        link.addLast(compnew.first());
                        link.addLast(compnew.last());
                    } else {
                        continue;
                    }
                    compnew = new Linkedlist();

                }
            }
            link.addLast(-1);
            a[x + 1] = link;
            //nobp=nob;
        }
        else{
            throw new PixelOutOfBoundException("Check the input");
        }

    }

    public int[] numberOfBlackPixels() { return nobp;
    }
    
    public void invert()
    {
		//you need to implement this
		//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        try {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    this.setPixelValue(i, j, !this.getPixelValue(i, j));

                }
                nobp[i] = width - nobp[i];
            }
        }
        catch (PixelOutOfBoundException e){
            System.out.println("XOXO");
        }


    }
    
    public void performAnd(CompressedImageInterface img) throws BoundsMismatchException
    {
        LinkedListImage imglist=(LinkedListImage)img;
        try{
        if(height==imglist.height && width==imglist.width) {
            int[] arr = new int[height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    this.setPixelValue(i, j, this.getPixelValue(i, j) && img.getPixelValue(i, j));
                    if (!this.getPixelValue(i, j)) {
                        arr[i]++;
                    }
                }
            }
            nobp = arr;
        }
        else{
            throw new BoundsMismatchException("Check the size of image");
        }
        }
        catch (PixelOutOfBoundException e){
            System.out.println("XOxo");
        }
    }
    
    public void performOr(CompressedImageInterface img) throws BoundsMismatchException
    {
        LinkedListImage imglist=(LinkedListImage)img;
        try{
            if(height==imglist.height && width==imglist.width) {
                int[] arr = new int[height];
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        this.setPixelValue(i, j, this.getPixelValue(i, j) || img.getPixelValue(i, j));
                        if (!this.getPixelValue(i, j)) {
                            arr[i]++;
                        }
                    }
                }
                nobp = arr;
            }
            else{
                throw new BoundsMismatchException("Check the size of image");
            }
        }
        catch (PixelOutOfBoundException e){
            System.out.println("XOxo");
        }
    }
    
    public void performXor(CompressedImageInterface img) throws BoundsMismatchException
    {
        LinkedListImage imglist=(LinkedListImage)img;
        try{
            if(height==imglist.height && width==imglist.width) {
                int[] arr = new int[height];
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        this.setPixelValue(i, j, this.getPixelValue(i, j) ^ img.getPixelValue(i, j));
                        if (!this.getPixelValue(i, j)) {
                            arr[i]++;
                        }
                    }
                }
                nobp = arr;
            }
            else{
                throw new BoundsMismatchException("Check the size of image");
            }
        }
        catch (PixelOutOfBoundException e){
            System.out.println("XOxo");
        }
    }

    public String toStringUnCompressed()
    {
        //you need to implement this
        //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        String res = "";

        res+=height;
        res+=" ";
        res+=width;

        for (int x = 0 ; x< height ;x++){
            int[] arr=new int[width];
            Node ptr = a[x+1].head;
            Node temp = new Node();
            int cntr=2;

            if (a[x+1].size()>1) {
                for (int i = 0; i < a[x + 1].size() - 1; i=i+1) {
                    if (cntr == 1)
                        cntr--;
                    else if (cntr == 0)
                        cntr++;



                    if (cntr == 1) {
                        for (int j = temp.getElement() + 1; j < ptr.getElement(); j++) {
                            arr[j] = 1;
                        }
                    }

                    else if (cntr == 0){
                        for (int j = temp.getElement(); j <= ptr.getElement(); j++) {
                            arr[j] = 0;
                        }
                    }
                    else {
                        for (int j = 0; j < ptr.getElement(); j++) {
                            arr[j] = 1;
                        }
                        cntr = 1;

                    }
                    temp = ptr;
                    ptr = ptr.getNext();

                    if (ptr.getElement() == -1)
                    {
                        for (int j = temp.getElement()+1;j<width;j++){
                            arr[j]=1;
                        }
                        break;
                    }


                }
            }
            else{
                for (int i = 0; i <width ; i++) {
                    arr[i]=1;
                }
            }
            res+=",";
            for (int j = 0; j <width ; j++) {
                res+=" ";res+=String.valueOf(arr[j]);
            }

        }

        return res;



    }

    public String toStringCompressed()
    {
        //you need to implement this
        //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        String res = "";

        res+=height;
        res+=" ";
        res+=width;
        res+=", ";

        int i =1;
        while(i<=height-1){
            Node ref = a[i].head;
            while(ref.getNext()!=null){
                int h=ref.getElement();
                res+=h;
                res+=" ";
                ref=ref.getNext();
            }
            res+="-1";
            res+=',';
            res+=" ";
            i++;
        }

        Node ref = a[height].head;
        while(ref.getNext()!=null){
            int h = ref.getElement();
            res+=h;
            res+=" ";
            ref=ref.getNext();
        }

        res += "-1";
        return res;

    }

    public static void main(String[] args) {
    	// testing all methods here :
    	boolean success = true;

    	// check constructor from file
    	CompressedImageInterface img1 = new LinkedListImage("sampleInputFile.txt");

    	// check toStringCompressed
    	String img1_compressed = img1.toStringCompressed();
    	String img_ans = "16 16, -1, 5 7 -1, 3 7 -1, 2 7 -1, 2 2 6 7 -1, 6 7 -1, 6 7 -1, 4 6 -1, 2 4 -1, 2 3 14 15 -1, 2 2 13 15 -1, 11 13 -1, 11 12 -1, 10 11 -1, 9 10 -1, 7 9 -1";
    	success = success && (img_ans.equals(img1_compressed));

    	if (!success)
    	{
    		System.out.println("Constructor (file) or toStringCompressed ERROR");
    		return;
    	}

    	// check getPixelValue
    	boolean[][] grid = new boolean[16][16];
    	for (int i = 0; i < 16; i++)
    		for (int j = 0; j < 16; j++)
    		{
                try
                {
        			grid[i][j] = img1.getPixelValue(i, j);                
                }
                catch (PixelOutOfBoundException e)
                {
                    System.out.println("Errorrrrrrrr");
                }
    		}

    	// check constructor from grid
    	CompressedImageInterface img2 = new LinkedListImage(grid, 16, 16);
    	String img2_compressed = img2.toStringCompressed();
    	success = success && (img2_compressed.equals(img_ans));
    	if (!success)
    	{
    		System.out.println("Constructor (array) or toStringCompressed ERROR");
    		return;
    	}

    	// check Xor
        try
        {
        	img1.performXor(img2);       
        }
        catch (BoundsMismatchException e)
        {
            System.out.println("Errorrrrrrrr");
        }
    	for (int i = 0; i < 16; i++)
    		for (int j = 0; j < 16; j++)
    		{
                try
                {
        			success = success && (!img1.getPixelValue(i,j));                
                }
                catch (PixelOutOfBoundException e)
                {
                    System.out.println("Errorrrrrrrr");
                }
    		}

    	if (!success)
    	{
    		System.out.println("performXor or getPixelValue ERROR");
    		return;
    	}

    	// check setPixelValue
    	for (int i = 0; i < 16; i++)
        {
            try
            {
    	    	img1.setPixelValue(i, 0, true);            
            }
            catch (PixelOutOfBoundException e)
            {
                System.out.println("Errorrrrrrrr");
            }
        }

    	// check numberOfBlackPixels
    	int[] img1_black = img1.numberOfBlackPixels();
    	success = success && (img1_black.length == 16);
    	for (int i = 0; i < 16 && success; i++)
    		success = success && (img1_black[i] == 15);
    	if (!success)
    	{
    		System.out.println("setPixelValue or numberOfBlackPixels ERROR");
    		return;
    	}

    	// check invert
        img1.invert();
        for (int i = 0; i < 16; i++)
        {
            try
            {
                success = success && !(img1.getPixelValue(i, 0));            
            }
            catch (PixelOutOfBoundException e)
            {
                System.out.println("Errorrrrrrrr");
            }
        }
        if (!success)
        {
            System.out.println("invert or getPixelValue ERROR");
            return;
        }

    	// check Or
        try
        {
            img1.performOr(img2);        
        }
        catch (BoundsMismatchException e)
        {
            System.out.println("Errorrrrrrrr");
        }
        for (int i = 0; i < 16; i++)
            for (int j = 0; j < 16; j++)
            {
                try
                {
                    success = success && img1.getPixelValue(i,j);
                }
                catch (PixelOutOfBoundException e)
                {
                    System.out.println("Errorrrrrrrr");
                }
            }
        if (!success)
        {
            System.out.println("performOr or getPixelValue ERROR");
            return;
        }

        // check And
        try
        {
            img1.performAnd(img2);    
        }
        catch (BoundsMismatchException e)
        {
            System.out.println("Errorrrrrrrr");
        }
        for (int i = 0; i < 16; i++)
            for (int j = 0; j < 16; j++)
            {
                try
                {
                    success = success && (img1.getPixelValue(i,j) == img2.getPixelValue(i,j));             
                }
                catch (PixelOutOfBoundException e)
                {
                    System.out.println("Errorrrrrrrr");
                }
            }
        if (!success)
        {
            System.out.println("performAnd or getPixelValue ERROR");
            return;
        }

    	// check toStringUnCompressed
        String img_ans_uncomp = "16 16, 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1, 1 1 1 1 1 0 0 0 1 1 1 1 1 1 1 1, 1 1 1 0 0 0 0 0 1 1 1 1 1 1 1 1, 1 1 0 0 0 0 0 0 1 1 1 1 1 1 1 1, 1 1 0 1 1 1 0 0 1 1 1 1 1 1 1 1, 1 1 1 1 1 1 0 0 1 1 1 1 1 1 1 1, 1 1 1 1 1 1 0 0 1 1 1 1 1 1 1 1, 1 1 1 1 0 0 0 1 1 1 1 1 1 1 1 1, 1 1 0 0 0 1 1 1 1 1 1 1 1 1 1 1, 1 1 0 0 1 1 1 1 1 1 1 1 1 1 0 0, 1 1 0 1 1 1 1 1 1 1 1 1 1 0 0 0, 1 1 1 1 1 1 1 1 1 1 1 0 0 0 1 1, 1 1 1 1 1 1 1 1 1 1 1 0 0 1 1 1, 1 1 1 1 1 1 1 1 1 1 0 0 1 1 1 1, 1 1 1 1 1 1 1 1 1 0 0 1 1 1 1 1, 1 1 1 1 1 1 1 0 0 0 1 1 1 1 1 1";
        success = success && (img1.toStringUnCompressed().equals(img_ans_uncomp)) && (img2.toStringUnCompressed().equals(img_ans_uncomp));

        if (!success)
        {
            System.out.println("toStringUnCompressed ERROR");
            return;
        }
        else
            System.out.println("ALL TESTS SUCCESSFUL! YAYY!");
    }
}