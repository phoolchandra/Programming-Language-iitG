valid_position(X,Y) :-
    X > 0,
    Y > 0,
    X =< 8,
    Y =< 8.

posneg(X,DX,Y) :-
    Y is X+DX.
posneg(X,DX,Y) :-
    Y is X-DX.

possible_knight(X, Y, A, B) :-
    posneg(X,2,A),
    posneg(Y,1,B).
possible_knight(X, Y, A, B) :-
    posneg(X,1,A),
    posneg(Y,2,B).

move_knight([X,Y], [A,B]) :-
    possible_knight(X,Y,A,B),
    valid_position(A,B).

knight_move(P1,P1,[P1],C) :-
    C < 1.
knight_move(P1,PZ,[P1|PT],C) :-
    C >= 1,
    C1 is C-1,
    move_knight(P1,P2),
    knight_move(P2,PZ,PT,C1).

knight_move(P1,S) :-
    knight_move(P1,S,_,1).




possible_king(X, Y, A, B) :-
    posneg(X,1,A),
    posneg(Y,1,B).

possible_king(X, Y, A, B) :-
    B = Y,
    posneg(X,1,A).

possible_king(X, Y, A, B) :-
    A = X,
    posneg(Y,1,B).

move_king([X,Y], [A,B]) :-
    possible_king(X,Y,A,B),
    valid_position(A,B).

king_move(P1,P1,[P1],C) :-
    C < 1.
king_move(P1,PZ,[P1|PT],C) :-
    C >= 1,
    C1 is C-1,
    move_king(P1,P2),
    king_move(P2,PZ,PT,C1).

king_move(P1,S) :-
    king_move(P1,S,_,1).



possible_pawn(X, Y, A, B) :-
    posneg(X,1,A),
    posneg(Y,1,B).

possible_pawn(X, Y, A, B) :-
    A = X,
    posneg(Y,1,B).

move_pawn([X,Y], [A,B]) :-
    possible_pawn(X,Y,A,B),
    valid_position(A,B).

pawn_move(P1,P1,[P1],C) :-
    C < 1.
pawn_move(P1,PZ,[P1|PT],C) :-
    C >= 1,
    C1 is C-1,
    move_pawn(P1,P2),
    pawn_move(P2,PZ,PT,C1).

pawn_move(P1,S) :-
    pawn_move(P1,S,_,1).





start(1).
start(2).
start(3).
start(4).
start(5).
start(6).
start(7).
start(8).

possible_bishop(X, Y, A, B) :-
    start(Start),
    posneg(X,Start,A),
    posneg(Y,Start,B).

move_bishop([X,Y], [A,B]) :-
    possible_bishop(X,Y,A,B),
    valid_position(A,B).

bishop_move(P1,P1,[P1],C) :-
    C < 1.
bishop_move(P1,PZ,[P1|PT],C) :-
    C >= 1,
    C1 is C-1,
    move_bishop(P1,P2),
    bishop_move(P2,PZ,PT,C1).

bishop_move(P1,S) :-
    bishop_move(P1,S,_,1).





possible_rook(X, Y, A, B) :-
    start(Start),
    A = X,
    posneg(Y,Start,B).

possible_rook(X, Y, A, B) :-
    start(Start),
    Y = B,
    posneg(X,Start,A).

move_rook([X,Y], [A,B]) :-
    possible_rook(X,Y,A,B),
    valid_position(A,B).

rook_move(P1,P1,[P1],C) :-
    C < 1.
rook_move(P1,PZ,[P1|PT],C) :-
    C >= 1,
    C1 is C-1,
    move_rook(P1,P2),
    rook_move(P2,PZ,PT,C1).

rook_move(P1,S) :-
    rook_move(P1,S,_,1).





queen_move(P1,S) :-
    rook_move(P1,S,_,1).

queen_move(P1,S) :-
    bishop_move(P1,S,_,1).
