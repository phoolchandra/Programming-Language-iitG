
% convert string to list then calulate number of ways to decode it.
decode(String) :-
    string_chars(String, List),
    decode_sum(List,Sum,_),
    write("Count = "),
    write(Sum).

% given list check if head of list is less than 7
is_next_less_than_seven([X|_]) :- atom_number(X,N), N<7.

% base case for list sum
decode_sum([], 1, 0).

% decode_sum(L,Cj,Ci) Here L contain list of which sum is calculated Cj contain sum till current index, while Ci contain sum till j-1 index
decode_sum([X|Xs], Sum, Ci) :-
    decode_sum(Xs, S0 , C0),
    atom_number(X,N),
    % Ci contain number of possible way found for previous index
    Ci is S0,
    (   % If this digit is 0 ,then this digit do not form any valid character with this single digit
        N = 0 ->
        Sum is 0
    ;   % If this digit is 1 or If digit is smaller than 2 and next digit is smaller than 7, then this digits form a valid character
        (N =:= 1 ; N =:= 2, is_next_less_than_seven(Xs) )->
        Sum is S0 + C0
    ;   % If this digit is greater than 2 or If digit is 2 and next digit is greater than 6, then this digit can be used as character
        Sum is S0
    )
    .