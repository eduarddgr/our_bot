
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

package org.bamboomy.c44.bot.piecez;

import org.bamboomy.c44.bot.board.Board;
import org.bamboomy.c44.bot.board.Place;
import org.bamboomy.c44.bot.player.Player;

public class Bisshop extends LinePiece {

	private static final boolean DEBUG = false;

	public Bisshop(Place place, int color, Player player, String identifier, boolean moved, Board board, String md5, int oldColor) {

		super(place, color, player, identifier, PieceValue.BISSHOP, moved, board, md5, oldColor);
		
		addBisshopLinez(board);
		
		createBisshopCheckLinez(board);
	}

	@Override
	public void calculateMovez() {

		super.calculateMovez();

		addBisshopMovez();

		if (DEBUG) {

			System.out.println(movez.size());
		}

		inited = true;

		// setChecks();
	}

	@Override
	public void propagateValues() {

		propagateValuesForBissop();
	}

}
