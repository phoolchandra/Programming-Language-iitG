% fact containing all edges with start,end ,distance of edge
edge(g1,g5,4).
edge(g2,g5,6).
edge(g3,g5,8).
edge(g4,g5,9).
edge(g1,g6,10).
edge(g2,g6,9).
edge(g3,g6,3).
edge(g4,g6,5).
edge(g5,g7,3).
edge(g5,g10,4).
edge(g5,g11,6).
edge(g5,g12,7).
edge(g5,g6,7).
edge(g5,g8,9).
edge(g6,g8,2).
edge(g6,g12,3).
edge(g6,g11,5).
edge(g6,g10,9).
edge(g6,g7,10).
edge(g7,g10,2).
edge(g7,g11,5).
edge(g7,g12,7).
edge(g7,g8,10).
edge(g8,g9,3).
edge(g8,g12,3).
edge(g8,g11,4).
edge(g8,g10,8).
edge(g10,g15,5).
edge(g10,g11,2).
edge(g10,g12,5).
edge(g11,g15,4).
edge(g11,g13,5).
edge(g11,g12,4).
edge(g12,g13,7).
edge(g14,g12,8).
edge(g15,g13,3).
edge(g13,g14,4).
edge(g14,g17,5).
edge(g14,g18,4).
edge(g17,g18,8).

% start cantaining gate name for entry and end containing gate name for exit
start(g1).
start(g2).
start(g3).
start(g4).
end(g17).

% print list of path by taking head out recursively and printing it
print1([]) :- writeln("").
print1([H|T]) :-  write(" -> "),write(H),print1(T).
print([H|T]) :- write("Path: "),write(H),print1(T).

% it give path without cycle considering given edge are directed
% it check if edge exist between start and end if it does not exist it take mid gate and try to get path from mid to end
% while adding start and mid to path it checks that it is not visited so cycle is avoided
% edges are considered directed
path_without_cycle_directed(Start,End,_,[Start,End],W) :- edge(Start,End,W).
path_without_cycle_directed(Start,End,Path,[Start|R],W1) :- edge(Start,Mid,W), \+member(Mid,Path),
                          path_without_cycle_directed(Mid,End,[Mid|Path],R,W2), \+member(Start,R),W1 is W + W2.
path_without_cycle_directed(Start,End,R,W):-path_without_cycle_directed(Start,End,[],R,W).


% it give path without cycle considering given edge are undirected
% it check if edge exist between start and end if it does not exist it take mid gate and try to get path from mid to end
% while adding start and mid to path it checks that it is not visited so cycle is avoided
% edges are considered undirected
path_without_cycle_undirected(Start,End,_,[Start,End],W) :- (edge(Start,End,W);edge(End,Start,W)).
path_without_cycle_undirected(Start,End,Path,[Start|R],W1) :- (edge(Start,Mid,W);edge(Mid,Start,W)), \+member(Mid,Path),
                          path_without_cycle_undirected(Mid,End,[Mid|Path],R,W2), \+member(Start,R),W1 is W + W2.
path_without_cycle_undirected(Start,End,R,W):-path_without_cycle_undirected(Start,End,[],R,W).


% it give path with cycle considering given edge are undirected
% it check if edge exist between start and end if it does not exist it take mid gate and try to get path from mid to end
% edges are considered undirected
path_undirected(Start, End, [Start, End],W) :- edge(Start, End, W).
path_undirected(Start, End, [Start|Path],W) :- (edge(Start, Mid, W1);edge(Mid, Start, W1)),
                                                             path_undirected(Mid, End, Path,W2),W is W1 +W2.

% it give path with cycle considering given edge are directed
% it check if edge exist between start and end if it does not exist it take mid gate and try to get path from mid to end
% edges are considered directed
path_directed(Start, End, [Start, End],W) :- edge(Start, End, W).
path_directed(Start, End, [Start|Path],W) :- edge(Start, Mid, W1),
                                                           path_directed(Mid, End, Path,W2),W is W1 + W2.

% print all path from jail to exit
get_all_path :-
    findall(_,get_all_path(_,_,_),_).
get_all_path(Start,End,Path) :-
    start(Start),
    end(End),
    path_without_cycle_undirected(Start, End, Path,W),print(Path),write(" Distance = "),writeln(W),writeln("").


% check if edge exist between Gi and Gj
check(_,[],0).
check(Gi,[Gj|_],S) :- edge(Gi,Gj,S).
check(Gi,[Gj|_],S) :- edge(Gj,Gi,S).

% recursively go to end of list then check each pair that edge exist or not
valid([],0).
valid([Gi|Path],S) :-
    valid(Path,S2),
    check(Gi,Path,S1),
    S is S1 + S2.


% check if edge exist and check last gate is exit
check_endpoints(Gi,[]) :- end(Gi).
check_endpoints(Gi,[Gj|_]) :- edge(Gi,Gj,_).
check_endpoints(Gi,[Gj|_]) :- edge(Gj,Gi,_).

% check if starting vertex is jail
check_start([Gi|_]) :- start(Gi).

% check if starting vertex is jail and then validate all other pair to have an edge
valid_endpoints([]).
valid_endpoints(Path) :-
    check_start(Path),valid_check_endpoints(Path).

% recursively go to end of list and check end to be exit and then check between each pair of path that edge exist or not
valid_check_endpoints([]).
valid_check_endpoints([Gi|Path]) :-
    valid_check_endpoints(Path),
    check_endpoints(Gi,Path).


% recursion to get all path and distance without cycle
% it check if edge exist between start and end if it does not exist it take mid gate and try to get path from mid to end
% distance of path is distance from start to mid + distance from mid to end
% while adding start to path it checks that it is not visited so cycle is avoided
% edges are considered undirected
path_find(Start, End, Distance, [Start,End], _) :- edge(Start, End, Distance);edge(End, Start, Distance).
path_find(Start, End, Distance, [Start|Path], Visited) :- \+ member(Start, Visited),
                                 (edge(Start, Mid, Distance1);edge(Mid, Start, Distance1)),
                                 path_find(Mid, End, Distance2, Path, [Start|Visited]),
                                 Distance is Distance1 + Distance2.

% recursion to return shortest path when list is empty
min_dist_path([], M, MinPath, MinPath,M).

% recursion to update min path, when current path smaller than min path found
min_dist_path([(Distance, Path)|Paths], MinDistance, _, Output,O) :-
    Distance < MinDistance,
    min_dist_path(Paths, Distance, Path, Output,O).

% recursion , keep searching for path and when current path is not less than current min
min_dist_path([(Distance, _)|Paths], MinDistance, MinPath, Output,O) :-
    Distance >= MinDistance,
    min_dist_path(Paths, MinDistance, MinPath, Output,O).

% starting of search for min path by getting all path and then getting min from it
optimal(X) :-
    findall((Distance,Path),(start(Start),end(End),path_find(Start, End, Distance, Path, [])), R),
    aggregate_all(sum(Distance),edge(_,_,Distance),Sum),
    min_dist_path(R, Sum, [], X,OS),write("Sum = "),write(OS).
