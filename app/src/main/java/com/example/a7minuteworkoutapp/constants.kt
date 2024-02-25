package com.example.a7minuteworkoutapp

object constants {

    fun defaultExerciseList(): ArrayList<exerciseModel> {
        val exerciseList = ArrayList<exerciseModel>()

        val jumpingJacks = exerciseModel(1, "Jumping Jacks", R.drawable.ic_jumping_jacks, false, false)
        exerciseList.add(jumpingJacks)

        val wallSit = exerciseModel(2, "Wall Sit", R.drawable.ic_wall_sit, false, false)
        exerciseList.add(wallSit)

        val pushUp = exerciseModel(3, "Push Up", R.drawable.ic_push_up, false, false)
        exerciseList.add(pushUp)

        val abdominalCrunch = exerciseModel(4, "Abdominal Crunch", R.drawable.ic_abdominal_crunch, false, false)
        exerciseList.add(abdominalCrunch)

        val stepUpOnChair = exerciseModel(5, "Step-Up onto Chair", R.drawable.ic_step_up_onto_chair, false, false)
        exerciseList.add(stepUpOnChair)

        val squat = exerciseModel(6, "Squat", R.drawable.ic_squat, false, false)
        exerciseList.add(squat)

        val tricepDipOnChair = exerciseModel(7, "Tricep Dip On Chair", R.drawable.ic_triceps_dip_on_chair, false, false)
        exerciseList.add(tricepDipOnChair)

        val plank = exerciseModel(8, "Plank", R.drawable.ic_plank, false, false)
        exerciseList.add(plank)

        val highKneesRunningInPlace = exerciseModel(9, "High Knees Running In Place", R.drawable.ic_high_knees_running_in_place, false, false)
        exerciseList.add(highKneesRunningInPlace)

        val lunges = exerciseModel(10, "Lunges", R.drawable.ic_lunge, false, false)
        exerciseList.add(lunges)

        val pushupAndRotation = exerciseModel(11, "Push up and Rotation", R.drawable.ic_push_up_and_rotation, false, false)
        exerciseList.add(pushupAndRotation)

        val sidePlank = exerciseModel(12, "Side Plank", R.drawable.ic_side_plank, false, false)
        exerciseList.add(sidePlank)

        return exerciseList
    }
}