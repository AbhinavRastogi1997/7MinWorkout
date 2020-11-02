package com.example.a7minutesworkout

object Constants{
     fun excerciseList(): ArrayList<Excercise>{
        var excercises = ArrayList<Excercise>()

         val jumpingJacks = Excercise(1, "Jumping Jacks", R.drawable.ic_jumping_jacks, false, false)
         excercises.add(jumpingJacks)

         val wallSit = Excercise(2, "Wall Sit", R.drawable.ic_wall_sit, false, false)
         excercises.add(wallSit)

         val pushUp = Excercise(3, "Push Up", R.drawable.ic_push_up, false, false)
         excercises.add(pushUp)

         val abdominalCrunch = Excercise(4, "Abdominal Crunch", R.drawable.ic_abdominal_crunch, false, false)
         excercises.add(abdominalCrunch)

         val stepUpOnChair = Excercise(5, "Step-Up onto Chair", R.drawable.ic_step_up_onto_chair, false, false)
         excercises.add(stepUpOnChair)

         val squat = Excercise(6, "Squat", R.drawable.ic_squat, false, false)
         excercises.add(squat)

         val tricepDipOnChair = Excercise(7, "Tricep Dip On Chair", R.drawable.ic_triceps_dip_on_chair, false, false)
         excercises.add(tricepDipOnChair)

         val plank = Excercise(8, "Plank", R.drawable.ic_plank, false, false)
         excercises.add(plank)

         val highKneesRunningInPlace = Excercise(9, "High Knees Running In Place", R.drawable.ic_high_knees_running_in_place, false, false)
         excercises.add(highKneesRunningInPlace)

         val lunges = Excercise(10, "Lunges", R.drawable.ic_lunge, false, false)
         excercises.add(lunges)

         val pushupAndRotation = Excercise(11, "Push up and Rotation", R.drawable.ic_push_up_and_rotation, false, false)
         excercises.add(pushupAndRotation)

         val sidePlank = Excercise(12, "Side Plank", R.drawable.ic_side_plank, false, false)
         excercises.add(sidePlank)

         return excercises
     }
}