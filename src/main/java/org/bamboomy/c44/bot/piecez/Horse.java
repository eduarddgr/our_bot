
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

import java.util.ArrayList;

import org.bamboomy.c44.bot.board.Board;
import org.bamboomy.c44.bot.board.Place;
import org.bamboomy.c44.bot.player.Player;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Horse extends PlaceAttackingPiece {

	public Horse(Place place, int color, Player player, String identifier, boolean moved, Board board, String md5, int oldColor) {

		super(place, color, player, identifier, PieceValue.HORSE, moved, board, md5, oldColor);
	}

	@Override
	public void calculateMovez() {

		movez = new ArrayList<>();

		if (currentPlace.getX() + 2 < 12) {

			if (currentPlace.getY() + 1 < 12) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() + 2][currentPlace.getY()
						+ 1];

				handleOtherPlaceNE(otherPlace);
			}

			if (currentPlace.getY() - 1 >= 0) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() + 2][currentPlace.getY()
						- 1];

				handleOtherPlaceNE(otherPlace);
			}
		}

		if (currentPlace.getX() - 2 >= 0) {

			if (currentPlace.getY() + 1 < 12) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() - 2][currentPlace.getY()
						+ 1];

				handleOtherPlaceNE(otherPlace);
			}

			if (currentPlace.getY() - 1 >= 0) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() - 2][currentPlace.getY()
						- 1];

				handleOtherPlaceNE(otherPlace);
			}
		}

		if (currentPlace.getY() + 2 < 12) {

			if (currentPlace.getX() + 1 < 12) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() + 1][currentPlace.getY()
						+ 2];

				handleOtherPlaceNE(otherPlace);
			}

			if (currentPlace.getX() - 1 >= 0) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() - 1][currentPlace.getY()
						+ 2];

				handleOtherPlaceNE(otherPlace);
			}
		}

		if (currentPlace.getY() - 2 >= 0) {

			if (currentPlace.getX() + 1 < 12) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() + 1][currentPlace.getY()
						- 2];

				handleOtherPlaceNE(otherPlace);
			}

			if (currentPlace.getX() - 1 >= 0) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() - 1][currentPlace.getY()
						- 2];

				handleOtherPlaceNE(otherPlace);
			}
		}

		inited = true;
	}

	@Override
	public void propagateValues() {

		if (currentPlace.getX() + 2 < 12) {

			if (currentPlace.getY() + 1 < 12) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() + 2][currentPlace.getY()
						+ 1];

				attachMe(otherPlace, this);
			}

			if (currentPlace.getY() - 1 >= 0) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() + 2][currentPlace.getY()
						- 1];

				attachMe(otherPlace, this);
			}
		}

		if (currentPlace.getX() - 2 >= 0) {

			if (currentPlace.getY() + 1 < 12) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() - 2][currentPlace.getY()
						+ 1];

				attachMe(otherPlace, this);
			}

			if (currentPlace.getY() - 1 >= 0) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() - 2][currentPlace.getY()
						- 1];

				attachMe(otherPlace, this);
			}
		}

		if (currentPlace.getY() + 2 < 12) {

			if (currentPlace.getX() + 1 < 12) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() + 1][currentPlace.getY()
						+ 2];

				attachMe(otherPlace, this);
			}

			if (currentPlace.getX() - 1 >= 0) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() - 1][currentPlace.getY()
						+ 2];

				attachMe(otherPlace, this);
			}
		}

		if (currentPlace.getY() - 2 >= 0) {

			if (currentPlace.getX() + 1 < 12) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() + 1][currentPlace.getY()
						- 2];

				attachMe(otherPlace, this);
			}

			if (currentPlace.getX() - 1 >= 0) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() - 1][currentPlace.getY()
						- 2];

				attachMe(otherPlace, this);
			}
		}
	}

	@Override
	protected void calculateReach(Place to) {

		reach.clear();

		Place other;

		if (currentPlace.getX() + 2 < 12) {

			if (currentPlace.getY() + 1 < 12) {

				other = currentPlace.getBoard().getPlacez()[currentPlace.getX() + 2][currentPlace.getY() + 1];

				if (other != to) {

					reach.add(other);
				}
			}

			if (currentPlace.getY() - 1 >= 0) {

				other = currentPlace.getBoard().getPlacez()[currentPlace.getX() + 2][currentPlace.getY() - 1];

				if (other != to) {

					reach.add(other);
				}
			}
		}

		if (currentPlace.getX() - 2 >= 0) {

			if (currentPlace.getY() + 1 < 12) {

				other = currentPlace.getBoard().getPlacez()[currentPlace.getX() - 2][currentPlace.getY() + 1];

				if (other != to) {

					reach.add(other);
				}
			}

			if (currentPlace.getY() - 1 >= 0) {

				other = currentPlace.getBoard().getPlacez()[currentPlace.getX() - 2][currentPlace.getY() - 1];

				if (other != to) {

					reach.add(other);
				}
			}
		}

		if (currentPlace.getY() + 2 < 12) {

			if (currentPlace.getX() + 1 < 12) {

				other = currentPlace.getBoard().getPlacez()[currentPlace.getX() + 1][currentPlace.getY() + 2];

				if (other != to) {

					reach.add(other);
				}
			}

			if (currentPlace.getX() - 1 >= 0) {

				other = currentPlace.getBoard().getPlacez()[currentPlace.getX() - 1][currentPlace.getY() + 2];

				if (other != to) {

					reach.add(other);
				}
			}
		}

		if (currentPlace.getY() - 2 >= 0) {

			if (currentPlace.getX() + 1 < 12) {

				other = currentPlace.getBoard().getPlacez()[currentPlace.getX() + 1][currentPlace.getY() - 2];

				if (other != to) {

					reach.add(other);
				}
			}

			if (currentPlace.getX() - 1 >= 0) {

				other = currentPlace.getBoard().getPlacez()[currentPlace.getX() - 1][currentPlace.getY() - 2];

				if (other != to) {

					reach.add(other);
				}
			}
		}
	}

}
