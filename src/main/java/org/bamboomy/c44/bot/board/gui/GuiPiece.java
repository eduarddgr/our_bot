
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

package org.bamboomy.c44.bot.board.gui;

import org.bamboomy.c44.bot.piecez.Piece;
import org.bamboomy.c44.bot.player.Color;

import lombok.Getter;

@Getter
public class GuiPiece {

	private final String identifier;

	private final int color;

	private String md5 = "md5";

	private boolean pinned = false;

	private boolean moved = false;

	public GuiPiece(Piece piece, String color, boolean currentPlayer) {

		identifier = piece.getIdentifier();
		this.color = piece.getColor();

		this.pinned = piece.isPinned();

		moved = piece.getMoved().get(0);

		if (currentPlayer && Color.getBySeq(piece.getColor()).getName().equalsIgnoreCase(color)) {

			this.md5 = piece.getMd5();
		}
	}
}
