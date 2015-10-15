package fr.iutvalence.groupe8.eldwars.model.map;

import java.util.Random;

import fr.iutvalence.groupe8.eldwars.Pos;

/**
 * The GridGenerator class is used to generate random Grids.
 * 
 * @author Nicolas
 * @version 20150525
 */
public class GridGenerator
{
	private final static int MAX_DISTANCE_SPILL_WATER = 4;

	private final int gridWidth;

	private final int gridHeight;

	/**
	 * The GridGenerator constructor.
	 * 
	 * @param gridWidth
	 *            - The Grid's width.
	 * @param gridHeight
	 *            - The Grid's height.
	 */
	public GridGenerator(int gridWidth, int gridHeight)
	{
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
	}

	/**
	 * The default GridGenerator constructor.
	 * 
	 * @param gridWidth
	 *            - The Grid's width.
	 * @param gridHeight
	 *            - The Grid's height.
	 */
	public GridGenerator()
	{
		this(Map.DEFAULT_MAP_WIDTH, Map.DEFAULT_MAP_HEIGHT);
	}

	/**
	 * Generates a random Grid following an algorithm.
	 * 
	 * @return A Grid.
	 */
	public Grid nextGrid()
	{

		long time = System.currentTimeMillis() % 1000;

		Grid grid = new Grid(this.gridWidth, this.gridHeight);
		genRandomGrid(grid);
		removeAngles(grid);
		removeAloneSurfaces(grid, Surface.WATER);
		removeAloneSurfaces(grid, Surface.SAND);
		removeAloneSurfaces(grid, Surface.ROCK);
		removeSandWithoutWater(grid);
		spillWater(grid);
		spillWater(grid);
		removeAloneSurfaces(grid, Surface.WATER);
		removeAloneSurfaces(grid, Surface.SAND);
		removeAloneSurfaces(grid, Surface.ROCK);
		circleWaterBySand(grid);
		removeAngles(grid);
		removeAloneSurfaces(grid, Surface.WATER);
		removeAloneSurfaces(grid, Surface.SAND);
		removeAloneSurfaces(grid, Surface.ROCK);
		circleWaterBySand(grid);

		System.out.println("Generated the Grid in "
				+ (System.currentTimeMillis() % 1000 - time) + "ms.");

		return grid;
	}

	/**
	 * Place grass on the angles.
	 * 
	 * @param grid
	 *            - The grid to compute.
	 */
	private void removeAngles(Grid grid)
	{

		for (int grow1 = 0, grow2 = 0; grow1 < 3; grow1++)
		{
			grid.getCellAtSpecificsCoords(grow1, grow2).setCellSurface(Surface.GRASS);
			for (grow2 = 0; grow2 < 3; grow2++)
				grid.getCellAtSpecificsCoords(grow1, grow2).setCellSurface(Surface.GRASS);
		}

		grid.getCellAtSpecificsCoords(0, 3).setCellSurface(Surface.GRASS);
		grid.getCellAtSpecificsCoords(3, 0).setCellSurface(Surface.GRASS);
		grid.getCellAtSpecificsCoords(0, 4).setCellSurface(Surface.GRASS);
		grid.getCellAtSpecificsCoords(4, 0).setCellSurface(Surface.GRASS);

		grid.getCellAtSpecificsCoords(this.gridWidth - 1, this.gridHeight - 1).setCellSurface(Surface.GRASS);
		grid.getCellAtSpecificsCoords(this.gridWidth - 2, this.gridHeight - 1).setCellSurface(Surface.GRASS);
		grid.getCellAtSpecificsCoords(this.gridWidth - 1, this.gridHeight - 2).setCellSurface(Surface.GRASS);
		grid.getCellAtSpecificsCoords(this.gridWidth - 2, this.gridHeight - 2).setCellSurface(Surface.GRASS);
		grid.getCellAtSpecificsCoords(this.gridWidth - 3, this.gridHeight - 2).setCellSurface(Surface.GRASS);
		grid.getCellAtSpecificsCoords(this.gridWidth - 2, this.gridHeight - 3).setCellSurface(Surface.GRASS);
		grid.getCellAtSpecificsCoords(this.gridWidth - 1, this.gridHeight - 3).setCellSurface(Surface.GRASS);
		grid.getCellAtSpecificsCoords(this.gridWidth - 3, this.gridHeight - 1).setCellSurface(Surface.GRASS);

		grid.getCellAtSpecificsCoords(this.gridWidth - 4, this.gridHeight - 1).setCellSurface(Surface.GRASS);
		grid.getCellAtSpecificsCoords(this.gridWidth - 1, this.gridHeight - 4).setCellSurface(Surface.GRASS);
		grid.getCellAtSpecificsCoords(this.gridWidth - 5, this.gridHeight - 1).setCellSurface(Surface.GRASS);
		grid.getCellAtSpecificsCoords(this.gridWidth - 1, this.gridHeight - 5).setCellSurface(Surface.GRASS);
	}

	/**
	 * Remove Sand surfaces where no neighbor is water.
	 * 
	 * @param grid
	 *            - The grid to compute.
	 */
	private void removeSandWithoutWater(Grid grid)
	{
		for (int line = 0; line < this.gridWidth; line++)
		{
			for (int column = 0; column < this.gridHeight; column++)
			{

				if (grid.getCellAtSpecificsCoords(line, column).getCellSurface() == Surface.SAND)
				{
					if (line != this.gridWidth - 1
							&& grid.getCellAtSpecificsCoords(line + 1, column).getCellSurface() != Surface.WATER)
					{

						if (line != 0
								&& grid.getCellAtSpecificsCoords(line - 1, column).getCellSurface() != Surface.WATER)
						{

							if (column != this.gridHeight - 1
									&& grid.getCellAtSpecificsCoords(line, column + 1).getCellSurface() != Surface.WATER)
							{

								if (column != 0
										&& grid.getCellAtSpecificsCoords(line, column - 1)
												.getCellSurface() != Surface.WATER)
								{

									grid.getCellAtSpecificsCoords(line, column).setCellSurface(Surface.GRASS);
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Circles water with sand.
	 * 
	 * @param grid
	 *            - The grid to compute.
	 */
	private void circleWaterBySand(Grid grid)
	{
		for (int line = 0; line < this.gridWidth; line++)
		{
			for (int column = 0; column < this.gridHeight; column++)
			{

				if (grid.getCellAtSpecificsCoords(line, column).getCellSurface() == Surface.WATER)
				{
					if (line != this.gridWidth - 1
							&& grid.getCellAtSpecificsCoords(line + 1, column).getCellSurface() != Surface.WATER)
					{

						grid.getCellAtSpecificsCoords(line + 1, column).setCellSurface(Surface.SAND);

					}

					if (line != 0
							&& grid.getCellAtSpecificsCoords(line - 1, column).getCellSurface() != Surface.WATER)
					{

						grid.getCellAtSpecificsCoords(line - 1, column).setCellSurface(Surface.SAND);

					}

					if (column != this.gridHeight - 1
							&& grid.getCellAtSpecificsCoords(line, column + 1).getCellSurface() != Surface.WATER)
					{

						grid.getCellAtSpecificsCoords(line, column + 1).setCellSurface(Surface.SAND);

					}

					if (column != 0
							&& grid.getCellAtSpecificsCoords(line, column - 1).getCellSurface() != Surface.WATER)
					{

						grid.getCellAtSpecificsCoords(line, column - 1).setCellSurface(Surface.SAND);
					}
				}

			}
		}
	}

	/**
	 * Generates a random Grid.
	 * 
	 * @param grid
	 *            - The Grid to compute.
	 */
	private void genRandomGrid(Grid grid)
	{
		Random rand = new Random();

		for (int line = 0; line < this.gridWidth; line++)
		{
			for (int column = 0; column < this.gridHeight; column++)
			{

				int randInt = rand.nextInt(13); // Generates random integers
												// between 0 (inclusive) and 13
												// (exclusive).
				if (randInt >= 0 && randInt <= 10)
				{
					grid.getCellAtSpecificsCoords(line, column).setCellSurface(Surface.GRASS);
				} else if (randInt == 11)
				{
					grid.getCellAtSpecificsCoords(line, column).setCellSurface(Surface.ROCK);
				} else if (randInt == 12)
				{
					grid.getCellAtSpecificsCoords(line, column).setCellSurface(Surface.WATER);
				}

			}
		}

	}

	/**
	 * Replace the Surfaces that haven't neighbors of the same type by grass.
	 * 
	 * @param grid
	 *            - The Grid to compute.
	 * @param surface
	 *            - The Surface to search.
	 */
	private void removeAloneSurfaces(Grid grid, Surface surface)
	{
		for (int line = 0; line < this.gridWidth; line++)
		{
			for (int column = 0; column < this.gridHeight; column++)
			{

				if (grid.getCellAtSpecificsCoords(line, column).getCellSurface() == surface)
				{
					if (line != this.gridWidth - 1
							&& grid.getCellAtSpecificsCoords(line + 1, column).getCellSurface() == grid
									.getCellAtSpecificsCoords(line, column).getCellSurface())
					{

					} else if (line != 0
							&& grid.getCellAtSpecificsCoords(line - 1, column).getCellSurface() == grid
									.getCellAtSpecificsCoords(line, column).getCellSurface())
					{

					} else if (column != this.gridHeight - 1
							&& grid.getCellAtSpecificsCoords(line, column + 1).getCellSurface() == grid
									.getCellAtSpecificsCoords(line, column).getCellSurface())
					{

					} else if (column != 0
							&& grid.getCellAtSpecificsCoords(line, column - 1).getCellSurface() == grid
									.getCellAtSpecificsCoords(line, column).getCellSurface())
					{

					} else
					{
						grid.getCellAtSpecificsCoords(line, column).setCellSurface(Surface.GRASS);
					}
				}

			}
		}

	}

	/**
	 * Generates a random lake.
	 * 
	 * @param grid
	 *            - The Grid to compute.
	 */
	private void spillWater(Grid grid)
	{
		Random rand = new Random();
		Pos pos = new Pos(rand.nextInt(this.gridWidth), rand.nextInt(this.gridHeight));

		for (int line = 0; line < MAX_DISTANCE_SPILL_WATER - rand.nextInt((int) this.gridWidth / 16); line++)
		{
			for (int column = 0; column < MAX_DISTANCE_SPILL_WATER - rand.nextInt((int) this.gridHeight / 16); column++)
			{

				if (pos.getXCoord() + line < this.gridWidth && pos.getYCoord() + column < this.gridHeight)
					grid.getCellAtASpecificPos(pos.newPos(line, column)).setCellSurface(Surface.WATER);

				if (pos.getXCoord() - line >= 0 && pos.getYCoord() + column < this.gridHeight)
					grid.getCellAtASpecificPos(pos.newPos(-line, column)).setCellSurface(Surface.WATER);

				if (pos.getXCoord() - line >= 0 && pos.getYCoord() - column >= 0)
					grid.getCellAtASpecificPos(pos.newPos(-line, -column)).setCellSurface(Surface.WATER);

				if (pos.getXCoord() + line < this.gridWidth && pos.getYCoord() - column >= 0)
					grid.getCellAtASpecificPos(pos.newPos(line, -column)).setCellSurface(Surface.WATER);
			}
		}

	}

	/**
	 * Get the generator's Grid's width.
	 * 
	 * @return The generator's Grid's width.
	 */
	public int getGridWidth()
	{
		return gridWidth;
	}

	/**
	 * Get the generator's Grid's height.
	 * 
	 * @return The generator's Grid's height.
	 */
	public int getGridHeight()
	{
		return gridHeight;
	}

}
