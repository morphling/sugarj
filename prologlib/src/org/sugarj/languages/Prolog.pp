[
   Program              -- _1 _2 _3 _4,
   Program.2:iter-star  -- _1,
   Program.3:iter-star  -- _1,
   Program.4:iter-star  -- _1,
   ModuleDec            -- H hs=0[KW[":-"] KW["module"] KW["("] _1 KW[","] _2 KW[")"] KW["."]],
   ModuleReexport       -- H hs=0[KW[":-"] KW["reexport"] KW["("] _1 KW[","] _2 KW[")"] KW["."]],
   ModulePath           -- _1,
   ModuleImport         -- H hs=0[KW[":-"] KW["use_module"] KW["("] _1 KW[")"] KW["."]],
   ModuleImport         -- H hs=0[KW[":-"] KW["use_module"] KW["("] _1 KW[","] _2 KW[")"] KW["."]],
   NonUnitClause        -- _1 KW[":-"] _2 KW["."],
   UnitClause           -- _1 KW["."],
   Command              -- KW[":-"] _1 KW["."],
   Query                -- KW["?-"] _1 KW["."],
   ModuleHead           -- _1 KW[":"] _2,
   ModuleBody           -- _1 KW[":"] _2,
   BodyPlus             -- KW["+"] _1,
   BodyArrowSeq         -- _1 KW["->"] _2 KW[";"] _3,
   BodyArrow            -- _1 KW["->"] _2,
   BodyOr               -- _1 KW[";"] _2,
   BodyAnd              -- _1 KW[","] _2,
   BodyGoal             -- _1,
   Postfix              -- _1 _2,
   Prefix               -- _1 _2,
   Infix                -- _1 _2 _3,
   Assign               -- _1 KW["is"] _2,
   Func                 -- H hs=0[_1 KW["("] _2 KW[")"]],
   Func.2:iter-sep      -- _1 KW[","],
   Bracket              -- KW["("] _1 KW[")"],
   Curly                -- KW["{"] _1 KW["}"],
   String               -- _1,
   Var                  -- _1,
   Cut                  -- KW["!"],
   List                 -- KW["["] _1 KW["]"],
   List.1:iter-star-sep -- _1 KW[","],
   ListTl               -- KW["["] _1 KW["|"] _2 KW["]"],
   ListTl.1:iter-sep    -- _1 KW[","],
   TermList             -- _1,
   TermList.1:iter-sep  -- _1 KW[","],
   Atom                 -- _1,
   Functor              -- _1,
   Op                   -- _1,
   UnsignedNumber       -- _1,
   Number               -- _1 _2,
   Inf                  -- _1 _2,
   Nan                  -- _1 _2,
   Nat                  -- _1,
   Float                -- _1,
   Word                 -- _1,
   Symbol               -- _1,
   QuotedName           -- _1
]
