package com.directi.training.codesmells.smelly.chess;

import com.directi.training.codesmells.smelly.Position;

public class Move
{
    private Position _from, _to;

    public Move(Position from, Position to)
    {
        _from = from;
        _to = to;
    }

    public Position getFrom()
    {
        return _from;
    }

    public Position getTo()
    {
        return _to;
    }
}
