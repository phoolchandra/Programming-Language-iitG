module Main where

import System.IO
import Control.Monad (replicateM)
import Data.Function (on)
import Data.List     (sortBy)
import System.Random (randomRIO,randomRs,mkStdGen)
import Control.Applicative((<$>))

teams :: [[Char]]
teams = ["BS","CM","CH","CV","CS","DS","EE","HU","MA","ME","PH","ST"]      -- initial team list

--Function to combine two team list
combineTeams :: [[Char]] -> [[Char]] -> [[Char]]
combineTeams [] _ = []
combineTeams _ [] = []
combineTeams (x:xs) (y:ys) = (x ++ " vs " ++ y) : combineTeams xs ys

--function to combine team list and time list
combineTeamAndTime :: [[Char]] -> [[Char]] -> [[Char]]
combineTeamAndTime [] _ = []
combineTeamAndTime _ [] = []
combineTeamAndTime (x:xs) (y:ys) = (x ++ " " ++ y) : combineTeamAndTime xs ys

shuffle :: [a] -> IO [a]
shuffle xs = do
  ys <- replicateM (length xs)  (randomRIO (1 :: Int, 100000))  --generate list of random numbers map them with list of teams sort according to random numbers
  pure $ map fst ( sortBy (compare `on` snd) (zip xs ys))       -- this will shuffle the team list in random order every time

saveArr :: [String] -> IO ()
saveArr xs = do
          writeFile "test.txt" (unlines xs)

main :: IO ()
main = do
  ranl <- shuffle teams   -- Randomly change order of elements of the teams
  let (list1,list2) = splitAt 6 ranl          --split teams into two equal half
  let list3 = combineTeams list1 list2             -- combine both half to make pair of corresponding indexes
  let date = ["1-11 9:30 AM","1-11 7:30 PM","2-11 9:30 AM","2-11 7:30 PM","3-11 9:30 AM","3-11 7:30 PM"]  --teams of available time slots
  bar <- shuffle list3                      --Randomly change order of elements of the teams
  let list4 = combineTeamAndTime bar date               -- combine team pair and time slots lists
  saveArr list4                               --function call to save the final schedule



-- function to print elements of the teams
printElements :: [String] -> IO ()
printElements [] = return ()
printElements (x:xs) = do putStrLn x
                          printElements xs

-- function to split line on space
splitBySpace :: Eq a => a -> [a] -> [[a]]
splitBySpace x y = func x y [[]]
    where
        func x [] z = reverse $ map (reverse) z
        func x (y:ys) (z:zs) = if y==x then
            func x ys ([]:(z:zs))
        else
            func x ys ((y:z):zs)

checkString :: String -> [Char] -> IO ()
checkString x team = do let a = splitBySpace ' ' x    -- split line on space and get first element i.e. team name
                        if team == a!!0        -- if team in query equals first team in team teams pair
                             then do putStrLn x  -- print the fixture of that team
                             else if team == a!!2  --if team in query equals second team in team teams pair
                                       then do putStrLn x  -- print the fixture of that team
                                       else return ()

checkElements :: [String] -> [Char] -> IO ()
checkElements [] team = return ()
checkElements (x:xs) team = do checkString x team
                               checkElements xs team

--function to get fixture of all teams
allMatch :: IO ()
allMatch = do
   content <- readFile "test.txt"     --read the stored schedule line by line
   let fileLines = lines content
   printElements fileLines          -- print line on console

-- function to get fixture of requested team
match :: [Char] -> IO ()
match team = do
   content <- readFile "test.txt"
   let fileLines = lines content
   checkElements fileLines team            -- function call for checkElements

fixture :: [Char] -> IO ()
fixture team = do          --function to get fixture
   if team == "all"        -- if request is to get all team's schedule call allMatch function
     then do allMatch
     else if team `elem` teams   --find team in teams
             then do match team
             else putStrLn "Please enter correct team"


-- function to get next match
checkThis:: (Ord a, Fractional a) => String -> [Char] -> a -> IO ()
checkThis x date time = do
                                if time <= 9.5                     -- if time is less than 9.5 than next match will be at 9:30
                                     then do let m = "9:30"
                                             checkdatematchString x date m  -- function call
                                     else if time <= 19.5
                                          then do let m = "7:30"    -- if time is less than 19.5 than next match will be at 7:30
                                                  checkdatematchString x date m
                                          else if time < 24
                                              then do let m = "9:30"     -- if time is between 19.5 and 24 than next match on next date at 9:30
                                                      if date == "1-11"
                                                          then do checkdatematchString x "2-11" m
                                                          else if date == "2-11"
                                                              then do checkdatematchString x "3-11" m
                                                              else return ()
                                              else return ()


-- function call to get match with time and time of argument
checkdatematchString :: String -> [Char] -> [Char] -> IO ()
checkdatematchString x date time = do let a = splitBySpace ' ' x  -- split line by space
                                      if date == a!!3       -- check for date match
                                           then do if time == a!!4   -- check for time match
                                                        then putStrLn x
                                                        else return ()
                                           else return ()


checkmatch:: (Ord t, Fractional t) => [String] -> [Char] -> t -> IO ()
checkmatch [] date time = return ()    -- if teams is empty return
checkmatch (x:xs) date time = do checkThis x date time     -- function call
                                 checkmatch xs date time


-- function to get next match after current time
nextmatch:: (Num a, Ord a, Ord t, Fractional t, Show a) => a -> t -> IO ()
nextmatch date time = do
   if date < 1 || date > 31 || time < 0 || time >= 24   -- check for correct format of time
         then putStrLn "Please Enter date time in correct format"
         else if date > 3 || (date == 3 && time > 19.50)     --check if any match left
                  then putStrLn "All Matches are Over"
                  else do content <- readFile "test.txt"
                          let fileLines = lines content
                          let s = show date
                          let b = s ++ "-11"              -- concatenate month with data
                          checkmatch fileLines b time  --function call to check the match
