
/**

$$$License$$$

**/

package org.bamboomy.c44.bot.move;

import java.util.ArrayList;

import org.bamboomy.c44.bot.board.Board;
import org.bamboomy.c44.bot.board.Place;
import org.bamboomy.c44.bot.piecez.LinePiece;
import org.bamboomy.c44.bot.player.Player;

public class CheckLine {

	private int xDelta, yDelta;
	private Board board;
	private LinePiece linePiece;

	private ArrayList<Place> oneDirection = new ArrayList<Place>(), otherDirection = new ArrayList<Place>();

	public CheckLine(int xDelta, int yDelta, Board board, LinePiece linePiece) {

		this.xDelta = xDelta;
		this.yDelta = yDelta;
		this.board = board;
		this.linePiece = linePiece;

		update();
	}

	public void update() {

		oneDirection = new ArrayList<Place>();

		Place otherPlace = linePiece.getCurrentPlace();

		while (otherPlace != null) {

			if (otherPlace.getX() + xDelta < 11 && otherPlace.getY() + yDelta < 11 && otherPlace.getY() + yDelta > 0) {

				if (otherPlace != linePiece.getCurrentPlace()) {

					oneDirection.add(otherPlace);
				}

				otherPlace = board.getPlacez()[otherPlace.getX() + xDelta][otherPlace.getY() + yDelta];

			} else {

				otherPlace = null;
			}
		}

		otherDirection = new ArrayList<Place>();

		otherPlace = linePiece.getCurrentPlace();

		while (otherPlace != null) {

			if (otherPlace.getX() - xDelta > 0 && otherPlace.getY() - yDelta < 11 && otherPlace.getY() - yDelta > 0) {

				if (otherPlace != linePiece.getCurrentPlace()) {

					otherDirection.add(otherPlace);
				}

				otherPlace = board.getPlacez()[otherPlace.getX() - xDelta][otherPlace.getY() - yDelta];

			} else {

				otherPlace = null;
			}
		}
	}

	public void checkCheck(Player player) {

		for (Place place : oneDirection) {

			if (place.getPiece() != null && place.getPiece() != player.getKing()) {

				break;
			}

			if (place.getPiece() == player.getKing()) {

				player.setCheck(linePiece);
			}
		}

		for (Place place : otherDirection) {

			if (place.getPiece() != null && place.getPiece() != player.getKing()) {

				break;
			}

			if (place.getPiece() == player.getKing()) {

				player.setCheck(linePiece);
			}
		}
	}

}
