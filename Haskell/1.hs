
import Data.List
import Data.Ord
import System.IO

m :: [[Int]] -> Int
m [] = 0
m xs = sm xs

sm [] = 1                -- if list is empty return 1
sm (x:xs) = sum x * sm xs -- sums elements in list x and calls m for next list recursively and multiply all of them




greatest :: (Ord a, Ord b) => (b -> a) -> [b] -> b
freatest f [] = error "Error"
greatest f xs = snd $ maximum $ [(f x, x) | x <- xs]   -- form tuple of element of list with f and get maximum of it



toList :: Show a => [a] -> IO ()
toList xs = do                       -- Print list in Cons format
            printList xs
            putStrLn ""

printList :: Show a => [a] -> IO ()
printList [] = do                    -- If list is empty
               putStr " Empty "
               return()

printList [x] = do                    -- If list have only one list
                putStr "Cons "
                putStr . show $ x
                putStr " Empty "

printList (x:xs) = do                -- If list have one than one list
  putStr " Cons "
  putStr . show $ x
  putStr " ("
  printList xs                       -- call same function for remaining lists
  putStr ")"


data List a = Empty | Cons a (List a) deriving Show  --Haskell list declaration

toHaskellList :: (List e) -> [e]            -- add e in list
toHaskellList Empty = []                    -- if input is empty create empty Haskell list
toHaskellList (Cons el rem) = el : toHaskellList rem     --get input in given format store first element after "Cons" in el
                                                        -- add el into list








