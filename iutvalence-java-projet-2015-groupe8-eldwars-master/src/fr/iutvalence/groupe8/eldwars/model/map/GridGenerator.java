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

	/**
	 * The max distance to spill water.
	 */
	private final static int MAX_DISTANCE_SPILL_WATER = 4;

	/**
	 * The generated Grid width.
	 */
	private final int gridWidth;

	/**
	 * The generated Grid height.
	 */
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
			grid.getCell(grow1, grow2).setSurface(Surface.GRASS);
			for (grow2 = 0; grow2 < 3; grow2++)
				grid.getCell(grow1, grow2).setSurface(Surface.GRASS);
		}

		grid.getCell(0, 3).setSurface(Surface.GRASS);
		grid.getCell(3, 0).setSurface(Surface.GRASS);
		grid.getCell(0, 4).setSurface(Surface.GRASS);
		grid.getCell(4, 0).setSurface(Surface.GRASS);

		grid.getCell(this.gridWidth - 1, this.gridHeight - 1).setSurface(Surface.GRASS);
		grid.getCell(this.gridWidth - 2, this.gridHeight - 1).setSurface(Surface.GRASS);
		grid.getCell(this.gridWidth - 1, this.gridHeight - 2).setSurface(Surface.GRASS);
		grid.getCell(this.gridWidth - 2, this.gridHeight - 2).setSurface(Surface.GRASS);
		grid.getCell(this.gridWidth - 3, this.gridHeight - 2).setSurface(Surface.GRASS);
		grid.getCell(this.gridWidth - 2, this.gridHeight - 3).setSurface(Surface.GRASS);
		grid.getCell(this.gridWidth - 1, this.gridHeight - 3).setSurface(Surface.GRASS);
		grid.getCell(this.gridWidth - 3, this.gridHeight - 1).setSurface(Surface.GRASS);

		grid.getCell(this.gridWidth - 4, this.gridHeight - 1).setSurface(Surface.GRASS);
		grid.getCell(this.gridWidth - 1, this.gridHeight - 4).setSurface(Surface.GRASS);
		grid.getCell(this.gridWidth - 5, this.gridHeight - 1).setSurface(Surface.GRASS);
		grid.getCell(this.gridWidth - 1, this.gridHeight - 5).setSurface(Surface.GRASS);
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

				if (grid.getCell(line, column).getSurface() == Surface.SAND)
				{
					if (line != this.gridWidth - 1
							&& grid.getCell(line + 1, column).getSurface() != Surface.WATER)
					{

						if (line != 0
								&& grid.getCell(line - 1, column).getSurface() != Surface.WATER)
						{

							if (column != this.gridHeight - 1
									&& grid.getCell(line, column + 1).getSurface() != Surface.WATER)
							{

								if (column != 0
										&& grid.getCell(line, column - 1)
												.getSurface() != Surface.WATER)
								{

									grid.getCell(line, column).setSurface(Surface.GRASS);
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

				if (grid.getCell(line, column).getSurface() == Surface.WATER)
				{
					if (line != this.gridWidth - 1
							&& grid.getCell(line + 1, column).getSurface() != Surface.WATER)
					{

						grid.getCell(line + 1, column).setSurface(Surface.SAND);

					}

					if (line != 0
							&& grid.getCell(line - 1, column).getSurface() != Surface.WATER)
					{

						grid.getCell(line - 1, column).setSurface(Surface.SAND);

					}

					if (column != this.gridHeight - 1
							&& grid.getCell(line, column + 1).getSurface() != Surface.WATER)
					{

						grid.getCell(line, column + 1).setSurface(Surface.SAND);

					}

					if (column != 0
							&& grid.getCell(line, column - 1).getSurface() != Surface.WATER)
					{

						grid.getCell(line, column - 1).setSurface(Surface.SAND);
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
					grid.getCell(line, column).setSurface(Surface.GRASS);
				} else if (randInt == 11)
				{
					grid.getCell(line, column).setSurface(Surface.ROCK);
				} else if (randInt == 12)
				{
					grid.getCell(line, column).setSurface(Surface.WATER);
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

				if (grid.getCell(line, column).getSurface() == surface)
				{
					if (line != this.gridWidth - 1
							&& grid.getCell(line + 1, column).getSurface() == grid
									.getCell(line, column).getSurface())
					{

					} else if (line != 0
							&& grid.getCell(line - 1, column).getSurface() == grid
									.getCell(line, column).getSurface())
					{

					} else if (column != this.gridHeight - 1
							&& grid.getCell(line, column + 1).getSurface() == grid
									.getCell(line, column).getSurface())
					{

					} else if (column != 0
							&& grid.getCell(line, column - 1).getSurface() == grid
									.getCell(line, column).getSurface())
					{

					} else
					{
						grid.getCell(line, column).setSurface(Surface.GRASS);
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
					grid.getCell(pos.newPos(line, column)).setSurface(Surface.WATER);

				if (pos.getXCoord() - line >= 0 && pos.getYCoord() + column < this.gridHeight)
					grid.getCell(pos.newPos(-line, column)).setSurface(Surface.WATER);

				if (pos.getXCoord() - line >= 0 && pos.getYCoord() - column >= 0)
					grid.getCell(pos.newPos(-line, -column)).setSurface(Surface.WATER);

				if (pos.getXCoord() + line < this.gridWidth && pos.getYCoord() - column >= 0)
					grid.getCell(pos.newPos(line, -column)).setSurface(Surface.WATER);
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
