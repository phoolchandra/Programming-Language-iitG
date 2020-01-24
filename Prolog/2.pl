
% facts for calorie for each starter
starter("Corn Tikki",30).
starter("Tomato Soup", 20).
starter("Chilli Paneer", 40).
starter("Crispy chicken", 40).
starter("Papdi Chaat", 20).
starter("Cold Drink", 20).

% facts for calorie for each maindish item
mainDish("Kadhai Paneer with Butter / Plain Naan" ,50).
mainDish("Veg Korma with Butter / Plain Naan" ,40).
mainDish("Murgh Lababdar with Butter / Plain Naan"  ,50).
mainDish("Veg Dum Biryani with Dal Tadka" ,50).
mainDish("Steam Rice with Dal Tadka" ,40).

% facts for calorie for each desert item
desert("Ice-cream" ,20).
desert("Malai Sandwich" ,30).
desert("Rasmalai" ,10).

% facts to check validity of menu    format - <status,x,y,z> status-(hungry,notsohungry, diet)   x-starter, y-maindish, z- desert
menu(hungry,1,1,1).
menu(notSoHungry,1,1,0).
menu(notSoHungry,0,1,1).
menu(diet,0,0,1).
menu(diet,0,1,0).
menu(diet,1,0,0).


writer1([]) :- writeln("").
writer1([[A,_]|T]) :-  write(" , "),write(A),writer1(T).
writer([[A,_]|T]) :- write("Items: "), write(A),writer1(T).

% get all subset items of given list with condition calorie value less than given max value
subset([], [],0,_).
subset(L, [_|T],S,M) :- subset(L, T , S1,M), S = S1.
subset([[H,A]|T1], [[H,A]|T2],S,M) :- subset(T1, T2, S1,M), S = S1 + A,S=<M.

% rules to get valid items list for perticular combination of status and menu
% when hungry, get all items and ignore their calorie values
find_item(hungry,1,1,1) :- starter(A,_),mainDish(B,_),desert(C,_),write("Items: "),write(A),write(" , "),write(B),write(" , "),writeln(C).
%  item can be from maindish and starter or maindish and desert but total calorie in each pair must be less than equal to 80
find_item(notSoHungry,1,1,0) :- mainDish(B,C), starter(A,M), M+C =< 80,write("Items: "), write(A),write(" , "),writeln(B).
find_item(notSoHungry,0,1,1) :- mainDish(B,C), desert(A,M), M+C =< 80,write("Items: "), write(A),write(" , "), writeln(B).

% when on diet take all subset of any one (starter,maindish, desert) where calorie less than equal to 40
find_item(diet,0,0,1,R) :- subset(X,R,M,40), M > 0,writer(X).
find_item(diet,0,1,0,R) :- subset(X,R,M,40), M > 0,writer(X).
find_item(diet,1,0,0,R) :- subset(X,R,M,40), M > 0,writer(X).

% get all possible output for all values of arguments
find_items(hungry,1,1,1) :- findall(_,find_item(hungry,1,1,1),_).
find_items(notSoHungry,1,1,0) :- findall(_,find_item(notSoHungry,1,1,0),_).
find_items(notSoHungry,0,1,1) :- findall(_,find_item(notSoHungry,0,1,1),_).
find_items(diet,0,0,1) :- findall([A,Y],(desert(A,Y),Y=<40),R), findall(_,find_item(diet,0,0,1,R),_).
find_items(diet,0,1,0) :- findall([A,Y],(mainDish(A,Y),Y=<40),R), findall(_,find_item(diet,0,1,0,R),_).
find_items(diet,1,0,0) :- findall([A,Y],(starter(A,Y),Y=<40),R), findall(_,find_item(diet,1,0,0,R),_).



% code section to print output with repeatation of same dish allowed logic same as above with one speacial condition commented below

% adds give second element of list, here it contains nutrient
adds([],0).
adds([_,A],A).

% fill is used to form list by repeating X, N times.
fill([], _, 0,_,S) :- S=0.                      % terminating condition when N becomes zero
fill([], _, N,M,S) :- N < M,N>0,S=0.            % condition which helps to return every sublist formed
fill([X|Xs], X, N,M,S) :- succ(N0, N), fill(Xs, X, N0,M,S1),adds(X,A),S is S1 +A.   % call itself recursively for N times and add X to resulting list 
                                                                                     % every time

% combine(X,Y,Z) used to combine X and Y list and give combine result in Z
combine([],L,L).            % base case when it pass whole second list to resulting list
combine([H|T],L1,[H|L2])  :-  combine(T,L1,L2).     % it recursively pass head from first list to resulting list till first list become empty

% for allowing repeatation here we are considering all subsets with head being fixed
subset_repetition([], [],0,_).            % terminating case for recursion when input become empty
subset_repetition(L, [_|T],S,M) :- subset_repetition(L, T , S1,M), S = S1.      % remove head from list recursively till it become empty
subset_repetition(R, [[H,A]|T2],S,M) :- subset_repetition(T1, T2, S1,M-A),  N is div((M-S1),A), N>0, fill(R2,[H,A],N,N,S4),S is S4+S1,combine(R2,T1,R).     %   take head and get subset from remaining list, then add head number of time it can be added

% find item for diet with repetition allowed
find_item_repetition(diet,0,0,1,R) :- subset_repetition(X,R,M,40), M > 0,writer(X).
find_item_repetition(diet,0,1,0,R) :- subset_repetition(X,R,M,40), M > 0,writer(X).
find_item_repetition(diet,1,0,0,R) :- subset_repetition(X,R,M,40), M > 0,writer(X).

% it help to get all possible combination for diet with repetition
find_items_repetition(diet,0,0,1) :- findall([A,Y],(desert(A,Y),Y=<40),R), findall(_,find_item_repetition(diet,0,0,1,R),_).
find_items_repetition(diet,0,1,0) :- findall([A,Y],(mainDish(A,Y),Y=<40),R), findall(_,find_item_repetition(diet,0,1,0,R),_).
find_items_repetition(diet,1,0,0) :- findall([A,Y],(starter(A,Y),Y=<40),R), findall(_,find_item_repetition(diet,1,0,0,R),_).

% used to print the list by getting head and printing it
writer3([]) :- write("").
writer3([[A,_]|T]) :-  write(" , "),write(A),writer3(T).
writer2([[A,_]|T]) :- write(A),writer3(T).

% give all combination for not so hungry with many dish from maindish and (starter or desert)
find_item_complex(notSoHungry,1,1,0,K,R) :- subset(L,K,C,80),C>0, subset(X,R,M,80-C), M>0, write("Items: "), writer2(L),write(" , "),writer2(X),writeln("").
find_item_complex(notSoHungry,0,1,1,K,R) :- subset(L,K,C,80),C>0, subset(X,R,M,80-C), M>0 , write("Items: "), writer2(L), write(" , "),writer2(X),writeln("").
find_items_complex(notSoHungry,1,1,0) :- findall([I,J],mainDish(I,J),K), findall([A,Y],starter(A,Y),R),findall(_,find_item_complex(notSoHungry,0,1,1,K,R),_).
find_items_complex(notSoHungry,0,1,1) :- findall([I,J],mainDish(I,J),K), findall([A,Y],desert(A,Y),R),findall(_,find_item_complex(notSoHungry,0,1,1,K,R),_).