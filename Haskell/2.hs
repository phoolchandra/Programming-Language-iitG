module Anagram where

import Data.List

isAnagram :: Ord a => [a] -> [a] -> Bool
isAnagram x y = sort x == sort y                                       -- if both strings are equal after sort than they are anagram else not

checkAnagram :: (Num t, Ord a) => [a] -> [[a]] -> (t, [([a], [a])])
checkAnagram _ [] = (0,[])                                            -- Break condition when remaining string is empty return 0
checkAnagram t (x:xs) = do if isAnagram t x                            -- check if both strings t and x  are anagram
                               then do let (z,a) = checkAnagram t xs in (z+1,a ++ [(t,x)])   --check if t is anagram with any remaining substrings, increment z and add both x and t to ans list
                               else let (z,a) = checkAnagram t xs in (z,a)     -- if t and x are not anagram check for t and remaining substrings


getAnagram :: (Num t, Ord a) => [[a]] -> (t, [([a], [a])])
getAnagram [] = (0,[])                                              -- break condition when list is empty return 0 number of anagrams and empty list of anagrams
getAnagram (x:xs) = do let (y,a) = checkAnagram x xs                -- function call for printAnagram
                       let (z,b) = getAnagram xs                 -- recursion call to check for anagrams in remaining substrings
                       (z+y,a++b)                                   --  add results of both , current substring's and remaining substrings


getAllSubstring :: [a] -> [[a]]
getAllSubstring x = concatMap  (tail . inits) ( tails x)            -- get all nonempty substring

check :: (Foldable t, Show a, Ord a) => t [a] -> IO ()
check x = do if (length x) /= 2                                         -- Check if there are exactly two lists
                 then do print "String Format is Invalid"    -- If no error message
                 else do let m = concat x                               -- concatenate both lists
                         let t = getAllSubstring m                   -- Function call for getAllSubstring function and t will contain all subsets
                         let (a,b) = getAnagram t                    -- Function all for printElements function and a will be containing number of anagrams
                         print a                                        -- b will be containing list of all anagrams
                         print b
