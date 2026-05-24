
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

import org.bamboomy.c44.bot.board.Place;
import org.bamboomy.c44.bot.piecez.Piece;
import org.bamboomy.c44.bot.piecez.Tower;

import lombok.Getter;
import lombok.Setter;

public class Rocade extends Move {

	@Getter
	@Setter
	private Tower tower;

	private final Place towerToPlace;

	public Rocade(Place from, Place to, Piece piece, Tower tower, Place towerToPlace) {

		super(from, to, piece, "r");

		this.tower = tower;
		this.towerToPlace = towerToPlace;
	}
}
