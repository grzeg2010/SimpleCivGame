--
-- SimpleCivGame: Turn-based strategy game inspired by Sid Meier's Civilization V, built with Spring Boot.
-- Copyright (C) 2026 Grzegorz Śnieżko
--
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU General Public License as published by
-- the Free Software Foundation, either version 3 of the License, or
-- (at your option) any later version.
--
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU General Public License for more details.
--
-- You should have received a copy of the GNU General Public License
-- along with this program.  If not, see <https://www.gnu.org/licenses/>.
--

INSERT INTO unit(unit_type, posX, posY) VALUES ('SETTLER', 4,3);
INSERT INTO settler VALUES (1);

INSERT INTO terrain VALUES (0,0,'LAND');
INSERT INTO terrain VALUES (0,1,'LAND');
INSERT INTO terrain VALUES (0,2,'SEA');
INSERT INTO terrain VALUES (0,3,'SEA');
INSERT INTO terrain VALUES (0,4,'SEA');
INSERT INTO terrain VALUES (0,5,'LAND');

INSERT INTO terrain VALUES (1,0,'SEA');
INSERT INTO terrain VALUES (1,1,'SEA');
INSERT INTO terrain VALUES (1,2,'SEA');
INSERT INTO terrain VALUES (1,3,'SEA');
INSERT INTO terrain VALUES (1,4,'LAND');
INSERT INTO terrain VALUES (1,5,'LAND');

INSERT INTO terrain VALUES (2,0,'SEA');
INSERT INTO terrain VALUES (2,1,'SEA');
INSERT INTO terrain VALUES (2,2,'SEA');
INSERT INTO terrain VALUES (2,3,'LAND');
INSERT INTO terrain VALUES (2,4,'LAND');
INSERT INTO terrain VALUES (2,5,'LAND');

INSERT INTO terrain VALUES (3,0,'LAND');
INSERT INTO terrain VALUES (3,1,'LAND');
INSERT INTO terrain VALUES (3,2,'LAND');
INSERT INTO terrain VALUES (3,3,'LAND');
INSERT INTO terrain VALUES (3,4,'LAND');
INSERT INTO terrain VALUES (3,5,'LAND');

INSERT INTO terrain VALUES (4,0,'MOUNTAIN');
INSERT INTO terrain VALUES (4,1,'LAND');
INSERT INTO terrain VALUES (4,2,'LAND');
INSERT INTO terrain VALUES (4,3,'LAND');
INSERT INTO terrain VALUES (4,4,'LAND');
INSERT INTO terrain VALUES (4,5,'LAND');

INSERT INTO terrain VALUES (5,0,'MOUNTAIN');
INSERT INTO terrain VALUES (5,1,'MOUNTAIN');
INSERT INTO terrain VALUES (5,2,'MOUNTAIN');
INSERT INTO terrain VALUES (5,3,'LAND');
INSERT INTO terrain VALUES (5,4,'LAND');
INSERT INTO terrain VALUES (5,5,'LAND');

INSERT INTO terrain VALUES (6,0,'LAND');
INSERT INTO terrain VALUES (6,1,'MOUNTAIN');
INSERT INTO terrain VALUES (6,2,'MOUNTAIN');
INSERT INTO terrain VALUES (6,3,'MOUNTAIN');
INSERT INTO terrain VALUES (6,4,'LAND');
INSERT INTO terrain VALUES (6,5,'LAND');

INSERT INTO terrain VALUES (7,0,'LAND');
INSERT INTO terrain VALUES (7,1,'LAND');
INSERT INTO terrain VALUES (7,2,'LAND');
INSERT INTO terrain VALUES (7,3,'LAND');
INSERT INTO terrain VALUES (7,4,'LAND');
INSERT INTO terrain VALUES (7,5,'LAND');
