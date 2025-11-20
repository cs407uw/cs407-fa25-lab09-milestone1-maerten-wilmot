package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        // TODO: Call reset()
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }

        //I DID THIS BASED ON THE EQUATIONS WHICH IS WHAT IT SAID TO DO
        val a0x = accX //initializing the a_0 and a_1s (it matches the equations better this way)
        val a0y = accY
        val a1x = xAcc
        val a1y = yAcc

        //EQUATION 1
        velocityX += 0.5f * (a0x + a1x) * dT //update
        velocityY += 0.5f *(a0y+a1y)*dT //update

        //EQUATION 2
        posX += velocityX * dT + (1f/6f) * (dT*dT) * (3*a0x+a1x) //update
        posY += velocityY * dT + (1f/6f) * (dT*dT) * (3*a0y+a1y) //update

        accX = a1x //update
        accY = a1y //update

        checkBoundaries()
    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // TODO: implement the checkBoundaries function
        // (Check all 4 walls: left, right, top, bottom)
        //left wall is at 0
        //right wall is at width
        //bottom is at height
        //top wall is at 0 ?

        if (posX < 0f){ //left
            posX = 0f
            velocityX = 0f
            accX = 0f
        }

        if (posX > backgroundWidth - ballSize){ //right
            posX = backgroundWidth - ballSize
            velocityX = 0f
            accX = 0f
        }

        if (posY > backgroundHeight - ballSize){ //bottom i think bro
            posY = backgroundHeight - ballSize
            velocityY = 0f
            accY = 0f
        }

        if (posY < 0f) { //top
            posY = 0f
            velocityY = 0f
            accY = 0f
        }
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // TODO: implement the reset function
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)
        posX = backgroundWidth / 2f - ballSize/2
        posY = backgroundHeight / 2f - ballSize/2

        velocityX = 0f
        velocityY = 0f

        accX = 0f
        accY = 0f

        isFirstUpdate = true
    }
}