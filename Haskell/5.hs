import Control.Monad
import Data.Function
import Data.List
import Data.List.Split


l = ["AN01","GA10","MH19","RJ28","AP02","GJ11","ML20","SK29","AR03","HR12","MN21","TN30","AS04","HP13","MP22","TR31","BR05","JH14","MZ23","TS32","CG06" ,"JK15" , "NL24" , "RJ33", "CH07" , "KA16" , "OD25" , "UK34","DD08","KL17","PB26","UP35","DN09","LD18","PY27","WB36"]

splitt x y = func x y [[]]
    where
        func x [] z = reverse $ map (reverse) z
        func x (y:ys) (z:zs) = if y==x then
            func x ys ([]:(z:zs))
        else
            func x ys ((y:z):zs)

f xs = do
         x <- xs
         return $ show x

numcheck x
     | x `elem` (f [ y | y <- [1000..9999]]) = True
     | otherwise           = False

isValid' x
     | x `elem` ['A'..'Z'] = True
     | x `elem` ['a'..'z'] = True
     | otherwise           = False


check c
   | not(c!!0 `elem` l)                                                                 = "Invalid input, State code does not exist"
   | not(all isValid' (c!!1))                                                           = "Invalid input, The Aadhar number provided is not in correct format"
   | not(numcheck (c!!2) && numcheck (c!!3) && numcheck (c!!4))                         = "Invalid input, The Aadhar number provided is not in correct format"
   | not(fakenum c)                                                                     = "The aadhar card is fake."
   | otherwise                                                                          = "The aadhar card is valid."

validateAadhar x = do
                    if 24 == length x
                          then do let a = splitt ' ' x
                                  let m = check a
                                  print m
                          else print "Invalid input, Aadhar number consist of more than 20 characters"


reverseList [] = []
reverseList (x:xs) = reverseList xs ++ [x]

reqList = do let m = reverseList ['A'..'Z']
             let a = ['0'..'9']
             a ++ m

alphaNum [] = []
alphaNum (x:xs) = (elemIndices x reqList) : alphaNum xs

sumString x = do let a = read x :: Integer
                 a

stringtoList x = do let t = alphaNum x
                    let m = show t
                    let n = split (dropDelims . dropBlanks $ oneOf " ,[]") m
                    let r = concat n
                    let s = map (read . (:"")) r :: [Int]
                    sumoflist2 s

sumoflist [] = 0
sumoflist (x:xs) = (stringtoList x) + sumoflist xs

fakenum c = do let a = sumoflist c
               0 == a `mod` 97

sumoflist2 [] = 0
sumoflist2 (x:xs) = x + sumoflist2 xs