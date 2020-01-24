Prolog installation in ubuntu terminal :- <br/>
sudo apt-get update <br/>
sudo add-apt-repository ppa:swi-prolog/stable <br/>
sudo apt-get install swi-prolog <br/>

Problem statement 1 -- <br/> 
run command -  swipl -s 1.pl <br/>
test cases execute  - decode(“7”). <br/>
                    - decode(“25”). <br/>
 decode print number of possible way to decode the string. <br/>
 
 Problem statement 2 -- <br/>
 run command -  swipl -s 2.pl <br/> 
 test cases execute -   menu(Status,X,Y,Z). <br/>
                        find_items(hungry,1,1,1). <br/>
                        find_items(notSoHungry,1,1,0). <br/>
                        find_items(notSoHungry,0,1,1). <br/>
                        find_items(diet,1,0,0). <br/>
                        find_items(diet,0,1,0). <br/>
                        find_items(diet,0,0,1). <br/>
                        find_items_repetition(diet,1,0,0). <br/>
                        find_items_repetition(diet,0,1,0). <br/>
                        find_items_repetition(diet,0,0,1). <br/>
                        find_items_complex(notSoHungry,0,1,1).    <br/>
                        find_items_complex(notSoHungry,1,1,0).   <br/>
 
 Problem statement 3 --  <br/>
  run command -  swipl -s 3.pl <br/>
  test cases execute -  get_all_path. <br/>
                        path_undirected(g1,g17,P). <br/>
                        path_directed(g1,g17,P). <br/>
                        path_without_cycle_directed(g1,g17,P). <br/>
                        valid([g1, g6, g8, g9, g10, g15, g13, g14, g17]). <br/>
                        valid_endpoints([g1, g6, g8, g9, g10, g15, g13, g14, g17]). <br/>
                        optimal(X).                                    
