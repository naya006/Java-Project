# Java Project

Student Module Tracker

A small Java console application that records a student's details, modules, and grades. It uses a simple menu so you can add, edit, grade, and remove modules in any order, then view a results table with a credit-weighted average and a degree classification.

- Menu-driven: add, update, edit, remove, and view modules until you choose to quit
- Each module has a code, name, credit value, and an optional grade
- Modules with no grade yet are shown as N/A
- Table of all modules and grades, with total credits and the weighted average of graded modules
- Degree classification worked out from the weighted average (First, 2:1, 2:2, Third, Fail)
- Edit a module's code, name or credits, and remove modules (with confirmation)
- Clear a grade by pressing Enter, which puts the module back to N/A
- Input validation: blank answers, non-numeric numbers, grades outside 0-100, credits outside 1-120, duplicate module codes, and unknown module codes are all handled with a message instead of a crash
- Grades are numbers from 0 to 100 (for example 65 or 72.5). Press Enter instead of typing a grade if a module has no grade yet.
- Module codes are not case sensitive, so comp1001 finds COMP1001.
- You can answer yes, y, no or n (any case) to confirmation questions.

The Average and Classification Work

- The weighted average is sum(grade x credits) / sum(credits), using only modules that have a grade.
- The classification uses these boundaries: 70+ First Class, 60+ Upper Second (2:1), 50+ Lower Second (2:2), 40+ Third Class, below 40 Fail.
- These are the common UK boundaries. Real degree classification depends on your university's rules (year weightings, rounding and borderline cases), so treat the result as a guide.
