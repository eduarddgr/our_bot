
/**

Copyright 2026 Sander Theetaert

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.


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
